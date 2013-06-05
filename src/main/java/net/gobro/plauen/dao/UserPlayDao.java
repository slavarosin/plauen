package net.gobro.plauen.dao;

import java.util.Calendar;
import java.util.List;

import net.gobro.plauen.model.UserPlay;


public interface UserPlayDao extends GenericDAO<UserPlay, Integer> {
	UserPlay fetch(String alias);

	List<UserPlay> findInactivePlayers(Calendar forDate);
}
