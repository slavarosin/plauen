package net.gobro.plauen.service.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import net.gobro.plauen.model.RoleEnum;
import net.gobro.plauen.model.Sms;
import net.gobro.plauen.model.User;
import net.gobro.plauen.service.NotificationService;
import net.gobro.plauen.service.SmsService;
import net.gobro.plauen.service.StatisticsService;
import net.gobro.plauen.service.UserService;

import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatisticsServiceImpl implements StatisticsService {

	@Autowired
	private NotificationService notificationService;

	@Autowired
	private UserService userService;

	@Autowired
	private SmsService smsService;

	@Override
	public void sendStats(boolean daily) {
		List<User> users = userService.fetchAll(RoleEnum.USER);
		String stats = "Users registered (total): " + users.size();

		String loggedToday = "";
		if (!daily) {
			Calendar today = Calendar.getInstance();

			today = DateUtils.truncate(today, Calendar.DATE);

			for (User user : users) {
				if (user.getLoggedIn().after(today)) {
					if (loggedToday.length() > 0)
						loggedToday += ", ";
					loggedToday += user.getLogin() + "(" + user.getCountry()
							+ ")";
				}
			}
		}

		users = userService.fetchAll(Calendar.getInstance());
		stats += "\nUsers registered (today): " + users.size();

		if (!daily) {
			DateFormat format = new SimpleDateFormat("dd.MM.yy HH:mm");

			List<Sms> sms = smsService.fetchAll(Calendar.getInstance());
			if (sms.size() > 0) {
				stats += "\n-----------------------------------------------";
				stats += "\nSMS(" + sms.size() + "):";
				for (Sms s : sms) {
					stats += "\n" + s.getPrice() + " -> " + s.getMessage()
							+ "(" + s.getUserPlay().getGame().getId() + ", "
							+ s.getUserPlay().getUser().getLogin() + ", "
							+ format.format(s.getTimestamp().getTime()) + ")";
				}
			}

			if (loggedToday.length() > 0) {
				stats += "\n-----------------------------------------------";
				stats += "\n" + loggedToday;
			}
		}

		if (daily) {
			notificationService.notifyByEmail("board@gobro.net", "Stats "
					+ Calendar.getInstance().getTime(), stats);
		} else {
			notificationService.notifyByEmail("slava@gobro.net", "Stats "
					+ Calendar.getInstance().getTime(), stats);
		}
	}
}
