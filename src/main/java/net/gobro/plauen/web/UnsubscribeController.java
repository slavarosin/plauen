package net.gobro.plauen.web;

import javax.servlet.http.HttpServletRequest;

import net.gobro.plauen.model.User;
import net.gobro.plauen.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/unsubscribe")
public class UnsubscribeController {

	@Autowired
	private UserService userService;

	@RequestMapping(method = RequestMethod.GET, params = { "email" })
	public String view(final HttpServletRequest request,
			@RequestParam final String email, final ModelMap model) {

		User user = userService.fetchByEmail(email);
		if (user != null) {
			user.setNotifications(0);
			userService.store(user);
			model.addAttribute("email", email);
			return "unsubscribed";
		}

		return "error";
	}
}
