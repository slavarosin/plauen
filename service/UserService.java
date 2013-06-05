package net.gobro.plauen.service;

import java.util.Calendar;
import java.util.List;

import net.gobro.plauen.model.RoleEnum;
import net.gobro.plauen.model.User;

public interface UserService {

	User fetch(Integer id);

	User fetch(String login);

	User store(User user);

	boolean isLoginExists(String login);

	boolean isEmailExists(String email);

	User fetchByEmail(String email);

	String generatePassword();

	List<User> fetchAll(RoleEnum role);

	List<User> fetchAll(Calendar timestamp);
	
	List<User> fetchAll(int notifications);

}