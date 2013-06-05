package net.gobro.plauen.web.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.gobro.plauen.model.CountryEnum;
import net.gobro.plauen.model.Game;
import net.gobro.plauen.service.GameService;
import net.gobro.plauen.service.SupportedLanguagesService;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.filter.GenericFilterBean;


public class CountryFilter extends GenericFilterBean implements
		InitializingBean {
	private static final Logger LOG = LoggerFactory
			.getLogger(CountryFilter.class);
	@Autowired
	private GameService gameService;
	private SupportedLanguagesService supportedLanguagesService;

	@Override
	public void afterPropertiesSet() throws ServletException {
		super.afterPropertiesSet();
		Assert.notNull(getSupportedLanguagesService(),
				"A supported languages service is required");
		Assert.notNull(gameService, "A game service is required");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		String path = httpRequest.getPathInfo();

		if ((path != null) && !path.startsWith("/country")
				&& !path.startsWith("/sms") && !path.startsWith("/error")) {
			CountryEnum country = null;

			if (httpRequest.getSession() != null) {
				country = (CountryEnum) httpRequest.getSession().getAttribute(
						"country");
			}

			if (!getSupportedLanguagesService().getCountries()
					.contains(country)) {
				HttpServletResponse httpResponse = (HttpServletResponse) response;

				// set continue path
				String continuePath = httpRequest.getRequestURI();

				if (httpRequest.getQueryString() != null) {
					continuePath += "?" + httpRequest.getQueryString();
				}

				httpRequest.getSession(true).setAttribute("continuePath",
						continuePath);

				String root = StringUtils.trimToNull(httpRequest
						.getContextPath());

				// if user go to the "game view" page
				if (path.startsWith("/game/view")) {
					try {
						Game game = gameService.fetch(Integer
								.parseInt(httpRequest.getParameter("id")));

						if (root == null) {
							httpResponse.sendRedirect("/do/country?country="
									+ game.getCountry());
						} else {
							httpResponse.sendRedirect(root
									+ "/do/country?country="
									+ game.getCountry());
						}

						return;
					} catch (Exception e) {
						LOG.error("Failed to load game with ID = ["
								+ httpRequest.getParameter("id") + "]", e);
					}
				}

				if (root == null) {
					root = "/";
				}

				// redirect
				if (LOG.isTraceEnabled()) {
					LOG.trace("Redirect to " + root + " with continuePath = "
							+ continuePath);
				}

				httpResponse.sendRedirect(root);

				return;
			}
		}

		chain.doFilter(request, response);
	}

	public SupportedLanguagesService getSupportedLanguagesService() {
		return supportedLanguagesService;
	}

	public void setSupportedLanguagesService(
			SupportedLanguagesService supportedLanguagesService) {
		this.supportedLanguagesService = supportedLanguagesService;
	}

}
