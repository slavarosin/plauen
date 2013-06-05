package net.gobro.plauen.service.impl;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import junit.framework.Assert;

import net.gobro.plauen.model.CountryEnum;
import net.gobro.plauen.model.LanguageEnum;
import net.gobro.plauen.service.SupportedLanguagesService;
import net.gobro.plauen.service.impl.SupportedLanguagesServiceImpl;

import org.junit.Before;
import org.junit.Test;


public class SupportedLanguagesServiceImplTest {

	private LinkedHashMap<CountryEnum, List<LanguageEnum>> supportedLanguages;
	private SupportedLanguagesService service;

	@Test
	public void testGetCountries() {
		List<CountryEnum> countries = service.getCountries();

		Assert.assertEquals("number of countries", 3, countries.size());

		Assert.assertEquals("first country", CountryEnum.EE, countries.get(0));
		Assert.assertEquals("second country", CountryEnum.LT, countries.get(1));
		Assert.assertEquals("third country", CountryEnum.LV, countries.get(2));
	}

	@Test
	public void testGetDefaultLanguageFor() {
		Assert.assertEquals(LanguageEnum.et, service
				.getDefaultLanguageFor(CountryEnum.EE));
		Assert.assertEquals(LanguageEnum.lt, service
				.getDefaultLanguageFor(CountryEnum.LT));
		Assert.assertEquals(LanguageEnum.lv, service
				.getDefaultLanguageFor(CountryEnum.LV));
	}

	@Test
	public void testGetLocales() {
		Map<CountryEnum, List<Locale>> locales = service.getLocales();

		Assert.assertEquals("number of countries", 3, locales.size());

		Assert.assertEquals("EE", locales.get(CountryEnum.EE).get(0)
				.getCountry());
		Assert.assertEquals("et", locales.get(CountryEnum.EE).get(0)
				.getLanguage());
		Assert.assertEquals("EE", locales.get(CountryEnum.EE).get(1)
				.getCountry());
		Assert.assertEquals("ru", locales.get(CountryEnum.EE).get(1)
				.getLanguage());
		Assert.assertEquals("EE", locales.get(CountryEnum.EE).get(2)
				.getCountry());
		Assert.assertEquals("en", locales.get(CountryEnum.EE).get(2)
				.getLanguage());
	}

	@Before
	public void setUp() {
		this.supportedLanguages = new LinkedHashMap<CountryEnum, List<LanguageEnum>>();
		LinkedList<LanguageEnum> languages = new LinkedList<LanguageEnum>();
		languages.add(LanguageEnum.et);
		languages.add(LanguageEnum.ru);
		languages.add(LanguageEnum.en);
		this.supportedLanguages.put(CountryEnum.EE, languages);
		languages = new LinkedList<LanguageEnum>();
		languages.add(LanguageEnum.lt);
		languages.add(LanguageEnum.en);
		this.supportedLanguages.put(CountryEnum.LT, languages);
		languages = new LinkedList<LanguageEnum>();
		languages.add(LanguageEnum.lv);
		languages.add(LanguageEnum.en);
		this.supportedLanguages.put(CountryEnum.LV, languages);

		service = new SupportedLanguagesServiceImpl(this.supportedLanguages);
	}

}
