package net.gobro.plauen.web.security;

import net.gobro.plauen.model.User;
import net.gobro.plauen.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


@Service
public class CurrentUserHelper {
	@Autowired
	private UserService userService;

	public User getCurrentUser() {
		final Object principal = SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		User currentUser = null;

		if (principal instanceof org.springframework.security.userdetails.User) {
			currentUser = userService.fetch(SecurityContextHolder.getContext()
					.getAuthentication().getName());
		}

		return currentUser;
	}
}
