package net.gobro.plauen.web.user;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.gobro.plauen.model.User;
import net.gobro.plauen.service.NotificationService;
import net.gobro.plauen.service.UserService;
import net.gobro.plauen.web.CountryLanguageHelper;
import net.gobro.plauen.web.beans.MessageBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/send")
public class SendMessageController {

	@Autowired
	private CountryLanguageHelper countryLanguageHelper;

	@Autowired
	private NotificationService notificationService;

	@Autowired
	private UserService userService;

	@ModelAttribute("messageBean")
	public MessageBean createCommand(HttpServletRequest request) {
		MessageBean messageBean = new MessageBean();

		messageBean.setLanguages(countryLanguageHelper
				.populateLanguages(request));

		return messageBean;
	}

	@RequestMapping(method = RequestMethod.GET)
	public String setupForm(final ModelMap model) {

		return "admin/send";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String send(
			@ModelAttribute("messageBean") final MessageBean messageBean,
			final ModelMap model) {

		List<String> texts = messageBean.getText();
		String message = "";
		for (String s : texts) {
			if (StringUtils.hasText(s)) {
				message += s + "\n\n";
			}
		}

		String emails = "";
		boolean send = false;
		if (StringUtils.hasText(message)) {
			List<User> users = userService.fetchAll(1);
			for (User user : users) {
				if (user.getEmail() != null) {
					if (user.getEmail().equals("sergey.kukarin@gmail.com")) {
						send = true;
					}
					if (!send)
						continue;
					if (notificationService.notifyByEmail(user.getEmail()
							.trim(), "GoBro.net - News", message)) {
						emails += user.getEmail().trim() + ", ";
					}
				}
			}
		}

		if (emails.length() > 0) {
			emails = emails.substring(0, emails.length() - 2);
		}

		model.addAttribute("message", "Message was sent to: " + emails);

		return "admin/send";
	}
}