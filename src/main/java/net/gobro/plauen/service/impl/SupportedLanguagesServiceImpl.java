package net.gobro.plauen.service.impl;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import net.gobro.plauen.model.CountryEnum;
import net.gobro.plauen.model.LanguageEnum;
import net.gobro.plauen.service.SupportedLanguagesService;


public class SupportedLanguagesServiceImpl implements SupportedLanguagesService {
	private List<CountryEnum> countries = null;

	private Map<CountryEnum, List<Locale>> locales = null;
	private Map<CountryEnum, List<LanguageEnum>> supportedLanguages = null;

	public SupportedLanguagesServiceImpl(
			Map<CountryEnum, List<LanguageEnum>> supportedLanguages) {
		// map of countries and languages
		this.supportedLanguages = new LinkedHashMap<CountryEnum, List<LanguageEnum>>();

		for (CountryEnum countryEnum : supportedLanguages.keySet()) {
			List<LanguageEnum> languages = new LinkedList<LanguageEnum>();

			this.supportedLanguages.put(countryEnum, languages);

			for (LanguageEnum languageEnum : supportedLanguages
					.get(countryEnum)) {
				if (!languages.contains(languageEnum)) {
					languages.add(languageEnum);
				}
			}
		}

		// list of countries
		this.countries = new LinkedList<CountryEnum>();

		for (CountryEnum countryEnum : this.supportedLanguages.keySet()) {
			this.countries.add(countryEnum);
		}

		// map of countries and locales
		this.locales = new LinkedHashMap<CountryEnum, List<Locale>>();

		for (CountryEnum countryEnum : this.supportedLanguages.keySet()) {
			List<Locale> list = new LinkedList<Locale>();

			for (LanguageEnum language : this.supportedLanguages
					.get(countryEnum)) {
				list.add(new Locale(language.name(), countryEnum.name()));
			}

			this.locales.put(countryEnum, list);
		}
	}

	/**
	 * @see net.gobro.plauen.service.SupportedLanguagesService#getCountries()
	 */
	public List<CountryEnum> getCountries() {
		return this.countries;
	}

	@Override
	public LanguageEnum getDefaultLanguageFor(CountryEnum country) {
		List<LanguageEnum> languages = getLanguagesFor(country);

		if (languages != null) {
			return languages.get(0);
		} else {
			return null;
		}
	}

	@Override
	public List<LanguageEnum> getLanguagesFor(CountryEnum country) {
		return this.supportedLanguages.get(country);
	}

	/**
	 * @see net.gobro.plauen.service.SupportedLanguagesService#getLocales()
	 */
	public Map<CountryEnum, List<Locale>> getLocales() {
		return locales;
	}
}
