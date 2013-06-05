package net.gobro.plauen.web.user;

import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.gobro.plauen.model.CountryEnum;
import net.gobro.plauen.model.RoleEnum;
import net.gobro.plauen.model.User;
import net.gobro.plauen.service.NotificationService;
import net.gobro.plauen.service.UserService;
import net.gobro.plauen.web.beans.SignUpBean;
import net.gobro.plauen.web.validator.SignUpValidator;

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
@RequestMapping("/user/signup")
public class SignUpController {

	private static final String FORM_VIEW = "user/signup";

	private static final String SUCCESS_VIEW = "redirect:/do/game/active?signup=successful";

	@Autowired
	private UserService userService;

	@Autowired
	private SignUpValidator validator;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private MessageSource messageSource;

	@Autowired
	private NotificationService notificationService;

	@RequestMapping(method = RequestMethod.GET)
	public String setupForm() {
		return FORM_VIEW;
	}

	@RequestMapping(method = RequestMethod.POST)
	public String signup(final HttpServletRequest request,
			final HttpSession session,
			@ModelAttribute("signup") final SignUpBean command,
			final BindingResult errors) {

		validator.validate(request, command, errors);

		if (errors.hasErrors()) {
			return FORM_VIEW;
		}

		User user = convertToModel(command);
		user.setLoggedIn(Calendar.getInstance());
		user.setSignedUp(Calendar.getInstance());
		user.setNotifications(1);
		user.setCountry(CountryEnum.valueOf(LocaleContextHolder.getLocale()
				.getCountry()));

		user = userService.store(user);

		String subject = messageSource.getMessage("signup.email.subject", null,
				LocaleContextHolder.getLocale());

		String text = messageSource.getMessage("signup.email.text",
				new String[] { user.getFirstName(), user.getLastName(),
						user.getLogin(), command.getPassword() },
				LocaleContextHolder.getLocale());

		if (!notificationService.notifyUser(user, subject, text)) {
			errors.rejectValue("email", "error.forgot.send",
					"Error sending email");
			return FORM_VIEW;
		}

		String message = messageSource.getMessage("signup.message.sent",
				new String[] { user.getEmail() }, LocaleContextHolder
						.getLocale());
		session.setAttribute("message", message);

		return SUCCESS_VIEW;
	}

	private User convertToModel(SignUpBean command) {
		User user = new User();
		user.setEmail(command.getEmail());
		user.setFirstName(command.getFirstName());
		user.setLastName(command.getLastName());
		user.setLogin(command.getLogin());
		user.setPasswd(passwordEncoder.encodePassword(command.getPassword(),
				null));
		user.setCity(command.getCity());
		user.setZip(command.getZip());
		user.setAddress(command.getStreet());
		user.setPhoneNumber(command.getPhone());
		user.setRole(RoleEnum.USER);
		return user;
	}

	@ModelAttribute("signup")
	public SignUpBean createCommand() {
		return new SignUpBean();
	}
}
