package net.gobro.plauen.log4j;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.log4j.helpers.LogLog;
import org.apache.log4j.net.SMTPAppender;
import org.apache.log4j.spi.ErrorCode;

public class GmailAppender extends SMTPAppender {
	private String jndiName;

	@Override
	public void activateOptions() {
		try {
			Context initCtx = new InitialContext();
			Context envCtx = (Context) initCtx.lookup("java:comp/env");

			Session session = (Session) envCtx.lookup(getJndiName());
			msg = new MimeMessage(session);

			try {
				if (getFrom() != null) {
					msg.setFrom(getAddress(getFrom()));
				} else {
					msg.setFrom();
				}

				msg.setRecipients(Message.RecipientType.TO,
						parseAddress(getTo()));
				if (getSubject() != null) {
					msg.setSubject(getSubject());
				}
			} catch (MessagingException e) {
				LogLog.error("Could not activate SMTPAppender options.", e);
			}
		} catch (NamingException e) {
			LogLog.error("Error occured while obtaining mail session via JNDI",
					e);
		}
	}

	InternetAddress getAddress(String addressStr) {
		try {
			return new InternetAddress(addressStr);
		} catch (AddressException e) {
			errorHandler.error("Could not parse address [" + addressStr + "].",
					e, ErrorCode.ADDRESS_PARSE_FAILURE);
			return null;
		}
	}

	InternetAddress[] parseAddress(String addressStr) {
		try {
			return InternetAddress.parse(addressStr, true);
		} catch (AddressException e) {
			errorHandler.error("Could not parse address [" + addressStr + "].",
					e, ErrorCode.ADDRESS_PARSE_FAILURE);
			return null;
		}
	}

	public void setJndiName(String jndiName) {
		this.jndiName = jndiName;
	}

	public String getJndiName() {
		return jndiName;
	}

}
