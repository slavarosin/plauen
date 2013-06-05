package net.gobro.plauen.web.filter;

import static org.springframework.web.servlet.i18n.SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;
import org.springframework.web.filter.GenericFilterBean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.util.WebUtils;

public class LocaleFilter extends GenericFilterBean implements InitializingBean {
	private List<LocaleResolver> localeResolvers;
	private Locale defaultLocale;

	@Override
	public void afterPropertiesSet() throws ServletException {
		super.afterPropertiesSet();
		Assert.notNull(localeResolvers,
				"A list of locale resolvers is required");
		Assert.notEmpty(localeResolvers,
				"A list of locale resolvers is required");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;

		if (WebUtils.getSessionAttribute(httpRequest,
				LOCALE_SESSION_ATTRIBUTE_NAME) == null) {
			Locale locale = null;

			for (LocaleResolver localeResolver : getLocaleResolvers()) {
				locale = localeResolver.resolveLocale(httpRequest);

				if (locale != null) {
					if (StringUtils.contains(locale.getLanguage(), "_")) {
						// create new locale object to remove "language variant"
						// from language code
						String lang = StringUtils.trimToNull(StringUtils
								.substring(locale.getLanguage(), 0, StringUtils
										.indexOf(locale.getLanguage(), "_")));
						String country = StringUtils.trimToNull(locale
								.getCountry());

						if (country != null && lang != null) {
							locale = new Locale(country, lang);
						} else {
							locale = null;
						}
					}

					break;
				}
			}

			if (locale == null && getDefaultLocale() != null) {
				locale = getDefaultLocale();
			}

			if (locale != null) {
				WebUtils.setSessionAttribute(httpRequest,
						LOCALE_SESSION_ATTRIBUTE_NAME, locale);
			}
		}

		chain.doFilter(request, response);
	}

	public void setLocaleResolvers(List<LocaleResolver> localeResolvers) {
		this.localeResolvers = localeResolvers;
	}

	public List<LocaleResolver> getLocaleResolvers() {
		return localeResolvers;
	}

	public void setDefaultLocale(Locale defaultLocale) {
		this.defaultLocale = defaultLocale;
	}

	public Locale getDefaultLocale() {
		return defaultLocale;
	}
}
