package net.gobro.plauen.web;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@Controller
@RequestMapping("/contacts")
public class ContactsController {

	@RequestMapping(method = RequestMethod.GET)
	public String view(final HttpServletRequest request, final ModelMap model) {
		Locale locale = (Locale) request.getSession().getAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME);;
		model.addAttribute("locale", locale);
		return "contacts";
	}
}
