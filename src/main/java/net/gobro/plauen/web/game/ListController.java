package net.gobro.plauen.web.game;

import static net.gobro.plauen.web.beans.GameConverter.convertToBeans;
import static org.springframework.web.servlet.i18n.SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME;

import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.gobro.plauen.model.CountryEnum;
import net.gobro.plauen.model.Game;
import net.gobro.plauen.model.LanguageEnum;
import net.gobro.plauen.model.User;
import net.gobro.plauen.service.GameService;
import net.gobro.plauen.service.SupportedLanguagesService;
import net.gobro.plauen.web.CountryLanguageHelper;
import net.gobro.plauen.web.beans.GameBean;
import net.gobro.plauen.web.security.CurrentUserHelper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.WebUtils;

@Controller
public class ListController {

	@Autowired
	private CountryLanguageHelper countryLanguageHelper;

	@Autowired
	private CurrentUserHelper currentUserHelper;

	@Autowired
	private GameService gameService;

	@Autowired
	private SupportedLanguagesService supportedLanguagesService;

	@RequestMapping("/game/active")
	public String showActiveGames(final ModelMap model, HttpSession session,
			HttpServletRequest request) {

		// current country
		CountryEnum currentCountry = countryLanguageHelper
				.getCurrentCountry(request);

		// default language
		LanguageEnum defaultLanguage = supportedLanguagesService
				.getDefaultLanguageFor(currentCountry);

		// current language
		Locale locale = (Locale) WebUtils.getSessionAttribute(request,
				LOCALE_SESSION_ATTRIBUTE_NAME);
		final LanguageEnum currentLanguage = LanguageEnum.valueOf(locale
				.getLanguage());

		// current user
		final User currentUser = currentUserHelper.getCurrentUser();

		// get list of active games sorted by "startedAt" parameter
		List<Game> games = gameService.fetchGamesOpenedForBidding(
				(CountryEnum) session.getAttribute("country"), Calendar
						.getInstance());

		if (games.isEmpty()) {
			return "game/empty";
		}

		List<GameBean> gameBeans = convertToBeans(games, currentUser,
				currentLanguage, defaultLanguage);

		model.addAttribute("games", gameBeans);
		model.addAttribute("mode", "active");

		return "game/list";
	}

	@RequestMapping("/game/list")
	public String showAllGames(final ModelMap model, HttpSession session) {
		// get list of all games sorted by "startedAt" parameter
		List<Game> games = gameService.fetchAll((CountryEnum) session
				.getAttribute("country"));

		if (games.isEmpty()) {
			return "game/empty";
		}

		model.addAttribute("games", games);
		model.addAttribute("mode", "all");

		return "game/admin/list";
	}
}
