package net.gobro.plauen.web.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.gobro.plauen.model.User;
import net.gobro.plauen.service.NotificationService;
import net.gobro.plauen.service.UserService;
import net.gobro.plauen.web.beans.ForgotPasswordBean;
import net.gobro.plauen.web.validator.Constant;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.providers.encoding.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping("/user/forgotPassword")
public class ForgotController {

	private static final String FORM_VIEW = "user/forgotPassword";

	private static final String SUCCESS_VIEW = "redirect:/do/game/active";

	@Autowired
	private UserService userService;

	@Autowired
	private NotificationService notificationService;

	@Autowired
	private MessageSource messageSource;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@RequestMapping(method = RequestMethod.GET)
	public String setupForm() {
		return FORM_VIEW;
	}

	@RequestMapping(method = RequestMethod.POST)
	public String signup(final HttpServletRequest request,
			final HttpSession session,
			@ModelAttribute("forgot") final ForgotPasswordBean command,
			final BindingResult errors) {

		if (StringUtils.isEmpty(command.getLogin())
				&& StringUtils.isEmpty(command.getEmail())) {
			errors.rejectValue("email", "error.forgot.empty",
					"Please fill at least one field!");
			return FORM_VIEW;
		}

		User user = null;
		if (StringUtils.isNotEmpty(command.getLogin())) {
			user = userService.fetch(command.getLogin());
		}

		if (user == null) {
			if (StringUtils.isEmpty(command.getEmail())) {
				errors.rejectValue("login", "error.forgot.nouser");
				return FORM_VIEW;
			}

			if (!command.getEmail().toLowerCase()
					.matches(Constant.EMAIL_FORMAT)) {
				errors.rejectValue("email", "error.email.invalid",
						"E-mail is invalid!");
				return FORM_VIEW;
			}

			if (StringUtils.isNotEmpty(command.getEmail())) {
				user = userService.fetchByEmail(command.getEmail());
			}
		}

		if (user == null) {
			errors.rejectValue("login", "error.forgot.nouser");
			return FORM_VIEW;
		}

		String password = userService.generatePassword();

		user.setPasswd(passwordEncoder.encodePassword(password, null));

		userService.store(user);

		String subject = messageSource.getMessage("forgot.email.subject", null,
				LocaleContextHolder.getLocale());

		String text = messageSource.getMessage("forgot.email.text",
				new String[] { user.getFirstName(), user.getLastName(),
						password, user.getLogin() }, LocaleContextHolder
						.getLocale());

		if (!notificationService.notifyUser(user, subject, text)) {
			errors.rejectValue("email", "error.forgot.send",
					"Error sending email");
			return FORM_VIEW;
		}

		String message = messageSource.getMessage("forgot.message.sent",
				new String[] { user.getEmail() }, LocaleContextHolder
						.getLocale());
		session.setAttribute("message", message);
		return SUCCESS_VIEW;
	}

	@ModelAttribute("forgot")
	public ForgotPasswordBean createCommand() {
		return new ForgotPasswordBean();
	}
}
