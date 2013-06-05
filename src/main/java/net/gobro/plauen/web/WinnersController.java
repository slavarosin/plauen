package net.gobro.plauen.web;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import net.gobro.plauen.model.CountryEnum;
import net.gobro.plauen.model.Winners;
import net.gobro.plauen.service.WinnersService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.util.WebUtils;

@RequestMapping("/winners")
public class WinnersController {

	@Autowired
	private WinnersService winnersService;

	private int numberOfWinners;

	@RequestMapping(method = RequestMethod.GET)
	public String view(final HttpServletRequest request, final ModelMap model) {
		Locale locale = (Locale) request.getSession().getAttribute(
				SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME);

		model.addAttribute("locale", locale);

		List<Winners> winners = winnersService.fetchLast(getNumberOfWinners(),
				(CountryEnum) WebUtils.getSessionAttribute(request, "country"));
		request.setAttribute("winners", winners);

		return "winners";
	}

	public int getNumberOfWinners() {
		return numberOfWinners;
	}

	public void setNumberOfWinners(int numberOfWinners) {
		this.numberOfWinners = numberOfWinners;
	}
}
