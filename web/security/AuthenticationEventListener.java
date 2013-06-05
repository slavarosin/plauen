package net.gobro.plauen.web.security;

import java.util.Calendar;

import net.gobro.plauen.model.User;
import net.gobro.plauen.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.security.event.authentication.AuthenticationSuccessEvent;
import org.springframework.security.userdetails.UserDetails;

public class AuthenticationEventListener implements ApplicationListener {

	@Autowired
	private UserService userService;

	public void onApplicationEvent(ApplicationEvent event) {
		if (event instanceof AuthenticationSuccessEvent) {
			AuthenticationSuccessEvent ev = (AuthenticationSuccessEvent) event;
			UserDetails userDetails = (UserDetails) ev.getAuthentication()
					.getPrincipal();

			String login = userDetails.getUsername();
			User user = userService.fetch(login);

			user.setLoggedIn(Calendar.getInstance());

			userService.store(user);
		}
	}

}