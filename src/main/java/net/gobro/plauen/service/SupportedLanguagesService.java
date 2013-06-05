package net.gobro.plauen.service;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import net.gobro.plauen.model.CountryEnum;
import net.gobro.plauen.model.LanguageEnum;


public interface SupportedLanguagesService {

	List<CountryEnum> getCountries();

	LanguageEnum getDefaultLanguageFor(CountryEnum country);

	List<LanguageEnum> getLanguagesFor(CountryEnum country);

	Map<CountryEnum, List<Locale>> getLocales();

}