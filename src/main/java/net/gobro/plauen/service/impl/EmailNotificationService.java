package net.gobro.plauen.service.impl;

import javax.mail.internet.MimeMessage;

import net.gobro.plauen.model.User;
import net.gobro.plauen.service.NotificationService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailNotificationService implements NotificationService {

	private static final Logger LOG = LoggerFactory
			.getLogger(EmailNotificationService.class);

	@Autowired
	private JavaMailSender mailSender;

	public boolean notifyUser(User user, String subject, String content) {
		return notifyByEmail(user.getEmail(), subject, content);
	}

	@Override
	public boolean notifyByEmail(String email, String subject, String content) {
		try {
			MimeMessage mimeMessage = mailSender.createMimeMessage();
			MimeMessageHelper message = new MimeMessageHelper(mimeMessage,
					"UTF-8");

			message.setSubject(subject);
			message.setText(content);
			message.setTo(email);

			mailSender.send(message.getMimeMessage());

			LOG
					.info("E-mail with subject " + subject + " is sent to: "
							+ email);
		} catch (Exception e) {
			LOG.error("Error in sending email", e);
			return false;
		}

		return true;
	}
}
