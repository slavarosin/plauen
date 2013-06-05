package net.gobro.plauen.web.validator;

import javax.servlet.http.HttpServletRequest;

import net.gobro.plauen.service.UserService;
import net.gobro.plauen.web.beans.SignUpBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.springframework.web.util.WebUtils;

import com.octo.captcha.service.image.ImageCaptchaService;


public class SignUpValidator implements Validator {

	@Autowired
	private ImageCaptchaService captchaService;

	@Autowired
	private UserService userService;

	@SuppressWarnings("unchecked")
	@Override
	public boolean supports(final Class c) {
		return c.equals(SignUpBean.class);
	}

	public void validate(final HttpServletRequest request, Object target,
			Errors errors) {

		validate(target, errors);
		if (errors.hasErrors()) {
			return;
		}

		final SignUpBean command = (SignUpBean) target;
		final String captcha = command.getCaptcha();
		final String id = WebUtils.getSessionId(request);

		ValidationUtils.rejectIfEmpty(errors, "captcha",
				"error.signup.captcha.empty", "Security code is empty!");
		if (errors.hasErrors()) {
			return;
		}
		if (!captchaService.validateResponseForID(id, captcha)) {
			errors.rejectValue("captcha", "error.signup.captcha.invalid",
					"Invalid security code!");
		}
	}

	@Override
	public void validate(Object target, Errors errors) {
		final SignUpBean command = (SignUpBean) target;

		/* Login validation */
		ValidationUtils.rejectIfEmpty(errors, "login",
				"error.signup.login.empty", "Login is empty!");
		if (errors.hasErrors()) {
			return;
		}
		if (userService.isLoginExists(command.getLogin())) {
			errors.rejectValue("login", "error.signup.login.exists",
					"Login already exists!");
			return;
		}

		/* E-mail validation */
		ValidationUtils.rejectIfEmpty(errors, "email",
				"error.signup.email.empty", "E-mail is empty!");
		if (errors.hasErrors()) {
			return;
		}
		if (userService.isEmailExists(command.getEmail())) {
			errors.rejectValue("email", "error.signup.email.exists",
					"E-mail already exists!");
			return;
		}
		if (!command.getEmail().toLowerCase().matches(Constant.EMAIL_FORMAT)) {
			errors.rejectValue("email", "error.email.invalid",
					"E-mail is invalid!");
			return;
		}

		/* Password validation */
		ValidationUtils.rejectIfEmpty(errors, "password",
				"error.signup.password.empty", "Password is empty!");
		ValidationUtils.rejectIfEmpty(errors, "passwordConfirm",
				"error.signup.password.empty", "Password is empty!");
		if (errors.hasErrors()) {
			return;
		}
		if (!command.getPassword().equals(command.getPasswordConfirm())) {
			errors.rejectValue("password", "error.signup.password.notequal",
					"Passwords are not equal!");
			return;
		}

		/* Phone validation */
		ValidationUtils.rejectIfEmpty(errors, "phone",
				"error.signup.phone.empty", "Phone is empty!");
		if (errors.hasErrors()) {
			return;
		}

		/* First name validation */
		ValidationUtils.rejectIfEmpty(errors, "firstName",
				"error.signup.firstname.empty", "First name is empty!");
		if (errors.hasErrors()) {
			return;
		}

		/* Last name validation */
		ValidationUtils.rejectIfEmpty(errors, "lastName",
				"error.signup.lastname.empty", "Last name is empty!");
		if (errors.hasErrors()) {
			return;
		}

		/* City validation */
		ValidationUtils.rejectIfEmpty(errors, "city",
				"error.signup.city.empty", "City is empty!");
		if (errors.hasErrors()) {
			return;
		}

		/* ZIP validation */
		ValidationUtils.rejectIfEmpty(errors, "zip", "error.signup.zip.empty",
				"ZIP is empty!");
		if (errors.hasErrors()) {
			return;
		}

		/* Street validation */
		ValidationUtils.rejectIfEmpty(errors, "street",
				"error.signup.street.empty", "Street is empty!");
		if (errors.hasErrors()) {
			return;
		}

		/** Terms of use validation */
		if (!command.isAgreedWithTermsOfUse()) {
			errors.rejectValue("agreedWithTermsOfUse",
					"error.signup.agreedWithTermsOfUse.declined",
					"You must agree with terms of use in order to register!");
			return;
		}
	}
}
