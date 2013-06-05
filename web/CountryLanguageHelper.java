package net.gobro.plauen.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.gobro.plauen.model.CountryEnum;
import net.gobro.plauen.model.LanguageEnum;
import net.gobro.plauen.service.SupportedLanguagesService;
import net.gobro.plauen.web.beans.CountryBean;
import net.gobro.plauen.web.beans.LanguageBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

@Service
public class CountryLanguageHelper {

	@Autowired
	private SupportedLanguagesService supportedLanguagesService;

	@Autowired
	private MessageSource messageSource;

	public List<LanguageBean> populateLanguages(HttpServletRequest request) {
		List<LanguageBean> languageBeans = new ArrayList<LanguageBean>();

		for (LanguageEnum language : supportedLanguagesService
				.getLanguagesFor(getCurrentCountry(request))) {
			LanguageBean languageBean = new LanguageBean();
			languageBean.setCode(language);
			languageBean.setName(messageSource.getMessage("language."
					+ language.name(), null, LocaleContextHolder.getLocale()));

			languageBeans.add(languageBean);
		}

		return languageBeans;
	}

	public CountryEnum getCurrentCountry(HttpServletRequest request) {
		return (CountryEnum) org.springframework.web.util.WebUtils
				.getSessionAttribute(request, "country");
	}

	public List<CountryBean> populateCountries() {
		List<CountryBean> countryBeans = new ArrayList<CountryBean>();

		for (CountryEnum country : supportedLanguagesService.getCountries()) {
			CountryBean countryBean = new CountryBean();
			countryBean.setCode(country);
			countryBean.setName(messageSource.getMessage("country."
					+ country.name(), null, LocaleContextHolder.getLocale()));

			countryBeans.add(countryBean);
		}

		return countryBeans;
	}
}
