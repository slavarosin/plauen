package net.gobro.plauen.dao;

import java.util.Calendar;
import java.util.List;

import net.gobro.plauen.model.RoleEnum;
import net.gobro.plauen.model.User;

public interface UserDao extends GenericDAO<User, Integer> {

	User fetch(String login, String passwd);

	User fetch(String login);

	User fetchByEmail(String email);

	List<User> fetchAll(RoleEnum role);

	List<User> fetchAll(Calendar timestamp);

	List<User> fetchAll(int notifications);

}
