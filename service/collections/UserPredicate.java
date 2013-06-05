package net.gobro.plauen.service.collections;

import net.gobro.plauen.model.User;

import org.apache.commons.collections.Predicate;


public final class UserPredicate implements Predicate {
	private final User user;

	public UserPredicate(User user) {
		this.user = user;
	}

	@Override
	public boolean evaluate(Object obj) {
		return ((User) obj).equals(user);
	}
}