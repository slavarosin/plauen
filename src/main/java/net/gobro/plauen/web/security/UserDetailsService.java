package net.gobro.plauen.web.security;

import net.gobro.plauen.model.User;
import net.gobro.plauen.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.GrantedAuthority;
import org.springframework.security.GrantedAuthorityImpl;
import org.springframework.security.userdetails.UserDetails;
import org.springframework.security.userdetails.UsernameNotFoundException;


public class UserDetailsService implements
		org.springframework.security.userdetails.UserDetailsService {
	@Autowired
	private UserService userService;

	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException, DataAccessException {
		User obj = userService.fetch(username);

		if (obj != null) {
			GrantedAuthority[] authorities = new GrantedAuthority[] { new GrantedAuthorityImpl(
					"ROLE_" + obj.getRole()) };

			UserDetails userDetails = new org.springframework.security.userdetails.User(
					obj.getLogin(), obj.getPasswd(), true, true, true, true,
					authorities);

			return userDetails;
		} else {
			throw new UsernameNotFoundException(username);
		}
	}

}
