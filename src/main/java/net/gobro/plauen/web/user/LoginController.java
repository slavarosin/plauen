package net.gobro.plauen.web.user;

import net.gobro.plauen.model.User;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class LoginController {
	@RequestMapping("/accessDenied")
	public String accessDenied() {
		return "redirect:user/login";
	}

	@RequestMapping(method = RequestMethod.GET, params = "embedded=true")
	public String setupEmbeddedLoginForm(final ModelMap model) {
		model.addAttribute("user", new User());

		return "user/login/embedded";
	}

	@RequestMapping(value = "/user/login", method = RequestMethod.GET, params = "!embedded")
	public String setupForm(final ModelMap model) {
		model.addAttribute("user", new User());
		model.addAttribute("hideEmbeddedLogin", Boolean.TRUE);

		return "user/login";
	}

}