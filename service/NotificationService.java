package net.gobro.plauen.service;

import net.gobro.plauen.model.User;

public interface NotificationService {

	boolean notifyUser(User user, String subject, String content);

	boolean notifyByEmail(String email, String subject, String content);

}
