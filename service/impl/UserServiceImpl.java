package net.gobro.plauen.service.impl;

import java.security.SecureRandom;
import java.util.Calendar;
import java.util.List;

import net.gobro.plauen.dao.UserDao;
import net.gobro.plauen.model.RoleEnum;
import net.gobro.plauen.model.User;
import net.gobro.plauen.service.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

	private static final int PASSWORD_LENGTH = 8;

	private static final String ALLOWED_PASSWORD_CHARS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";

	private static final Logger LOG = LoggerFactory
			.getLogger(UserServiceImpl.class);

	@Autowired
	private UserDao userDao;

	public User fetch(Integer id) {
		return getUserDao().fetch(id);
	}

	public User fetch(String login) {
		return getUserDao().fetch(login);
	}

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public User store(User user) {
		getUserDao().store(user);

		if (LOG.isInfoEnabled()) {
			LOG.info("User stored: " + user);
		}
		return user;
	}

	public boolean isLoginExists(String login) {
		return getUserDao().fetch(login) != null;
	}

	public boolean isEmailExists(String email) {
		return getUserDao().fetchByEmail(email) != null;
	}

	/**
	 * * Generates a random String that can be used as a password. * @return a
	 * random String of length 8 using only alphanumeric characters.
	 */
	public final String generatePassword() {
		final SecureRandom random = new SecureRandom();
		final StringBuilder salt = new StringBuilder();
		for (int i = 0; i < PASSWORD_LENGTH; i++) {
			salt.append(ALLOWED_PASSWORD_CHARS.charAt(random
					.nextInt(ALLOWED_PASSWORD_CHARS.length())));
		}
		return salt.toString();
	}

	public User fetchByEmail(String email) {
		return getUserDao().fetchByEmail(email);
	}

	public List<User> fetchAll(RoleEnum role) {
		return getUserDao().fetchAll(role);
	}

	public List<User> fetchAll(Calendar timestamp) {
		return getUserDao().fetchAll(timestamp);
	}

	public List<User> fetchAll(int notifications) {
		return getUserDao().fetchAll(notifications);
	}
}
