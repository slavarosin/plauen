package net.gobro.plauen.web;

import static org.springframework.web.servlet.i18n.SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.gobro.plauen.model.CountryEnum;
import net.gobro.plauen.service.SupportedLanguagesService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.WebUtils;

@Controller
@RequestMapping("/country")
public class CountryController {
	@Autowired
	private SupportedLanguagesService supportedLanguagesService;

	@RequestMapping(method = RequestMethod.GET)
	public String select(ModelMap model, HttpServletRequest request) {
		model.addAttribute("countries", supportedLanguagesService
				.getCountries());

		return "country";
	}

	@RequestMapping(method = RequestMethod.GET, params = { "country" })
	public String select(
			ModelMap model,
			HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value = "country", required = false) String country,
			@RequestParam(value = "continuePath", required = false) String continuePath)
			throws IOException {
		CountryEnum countryEnum = null;

		try {
			countryEnum = CountryEnum.valueOf(country);
		} catch (Exception _ignore) {
			return select(model, request);
		}

		List<Locale> supportedLocales = supportedLanguagesService.getLocales()
				.get(countryEnum);

		if (supportedLocales == null || supportedLocales.isEmpty()) {
			return select(model, request);
		}

		// store list of supported locales into the session
		WebUtils.setSessionAttribute(request, "supportedLocales",
				supportedLocales);

		// set session country
		WebUtils.setSessionAttribute(request, "country", countryEnum);

		Locale currentLocale = (Locale) WebUtils.getSessionAttribute(request,
				LOCALE_SESSION_ATTRIBUTE_NAME);
		Locale newLocale = null;

		// set session locale
		if (currentLocale == null) {
			newLocale = supportedLocales.get(0);
		} else {
			for (Locale locale : supportedLocales) {
				if (locale.getLanguage().equals(currentLocale.getLanguage())) {
					newLocale = locale;
					break;
				}
			}

			if (newLocale == null) {
				newLocale = supportedLocales.get(0);
			}
		}

		WebUtils.setSessionAttribute(request, LOCALE_SESSION_ATTRIBUTE_NAME,
				newLocale);

		if (continuePath != null) {
			if (request.getSession() != null) {
				request.getSession().removeAttribute("continuePath");
			}

			response.sendRedirect(continuePath);
			return null;
		} else {
			return "redirect:game/active";
		}
	}
}
