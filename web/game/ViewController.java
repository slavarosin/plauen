package net.gobro.plauen.web.game;

import static org.springframework.web.servlet.i18n.SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import net.gobro.plauen.model.Chart;
import net.gobro.plauen.model.ChartRow;
import net.gobro.plauen.model.CountryEnum;
import net.gobro.plauen.model.Game;
import net.gobro.plauen.model.LanguageEnum;
import net.gobro.plauen.model.RoleEnum;
import net.gobro.plauen.model.ScoreRule;
import net.gobro.plauen.model.User;
import net.gobro.plauen.service.BotService;
import net.gobro.plauen.service.ChartService;
import net.gobro.plauen.service.GameService;
import net.gobro.plauen.service.ScoreService;
import net.gobro.plauen.service.SupportedLanguagesService;
import net.gobro.plauen.service.UserService;
import net.gobro.plauen.service.collections.ChartRowPredicate;
import net.gobro.plauen.web.CountryLanguageHelper;
import net.gobro.plauen.web.beans.BotBean;
import net.gobro.plauen.web.beans.GameBean;
import net.gobro.plauen.web.beans.GameConverter;
import net.gobro.plauen.web.security.CurrentUserHelper;
import net.gobro.plauen.web.spring.CustomCalendarEditor;
import net.gobro.plauen.web.validator.BotValidator;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.WebUtils;

@Controller
public class ViewController {

	@Autowired
	private CountryLanguageHelper countryLanguageHelper;

	@Autowired
	private ApplicationContext applicationContext;

	@Autowired
	private ChartService chartService;

	@Autowired
	private CurrentUserHelper currentUserHelper;

	@Autowired
	private GameService gameService;

	@Autowired
	private UserService userService;

	@Autowired
	private SupportedLanguagesService supportedLanguagesService;

	@Autowired
	private BotValidator validator;

	@Autowired
	private BotService botService;

	public ScoreService getScoreService() {
		return (ScoreService) applicationContext.getBean("scoreService");
	}

	@InitBinder
	public void init(WebDataBinder binder, Locale locale) {
		binder.registerCustomEditor(Calendar.class, new CustomCalendarEditor(
				new SimpleDateFormat("dd.MM.yyyy"), true));
	}

	@RequestMapping(value = "/game/bot", method = RequestMethod.POST)
	public String addBot(final HttpServletRequest request,
			final ModelMap model, @ModelAttribute("bot") final BotBean command,
			final BindingResult errors) {

		validator.validate(request, command, errors);

		if (!errors.hasErrors()) {
			botService.addBot(command.getBotName(), command.getBotId(), command
					.getGameId());
		}

		return "redirect:/do/game/view?id=" + command.getGameId();
	}

	@RequestMapping(value = "/game/bots", method = RequestMethod.POST)
	public String addBots(final HttpServletRequest request,
			final ModelMap model, @ModelAttribute("bot") final BotBean command) {

		botService.addBots(command.getGameId());

		return "redirect:/do/game/view?id=" + command.getGameId();
	}

	@RequestMapping(value = "/game/bots/points", method = RequestMethod.POST)
	public String addPointsToBots(final HttpServletRequest request,
			final ModelMap model, @ModelAttribute("bot") final BotBean command) {

		CountryEnum currentCountry = countryLanguageHelper
				.getCurrentCountry(request);
		List<ScoreRule> scoreRules = getScoreService().getScoreRules(
				currentCountry);

		botService.addRandomPoints(command.getGameId(), scoreRules);

		return "redirect:/do/game/view?id=" + command.getGameId();
	}

	@RequestMapping(value = "/game/view", method = RequestMethod.GET, params = { "id" })
	public String viewGame(final ModelMap model,
			@ModelAttribute("bot") BotBean botBean,
			@RequestParam("id") final Integer id, HttpServletRequest request) {
		Game game = gameService.fetch(id);

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

		GameBean bean = GameConverter.convertToBean(game, currentUser,
				currentLanguage, defaultLanguage);

		model.addAttribute("game", bean);

		// chart
		Chart chart = chartService.fetchChart(game.getId());
		model.addAttribute("chart", chart);

		// user position
		if (currentUser != null) {
			ChartRow chartRow = (ChartRow) CollectionUtils.find(
					chart.getRows(), new ChartRowPredicate(currentUser
							.getLogin()));

			if (chartRow != null) {
				bean.setUserPosition(chart.getRows().indexOf(chartRow) + 1);
			}
		}

		// store values into model
		botBean.setGameId(game.getId());
		model.addAttribute("gameId", game.getId());
		model.addAttribute("scoreRules", getScoreService().getScoreRules(
				currentCountry));

		return "game/view";
	}

	@RequestMapping(method = RequestMethod.GET, params = { "activateAt" })
	public String viewGameByActivateAtDate(final ModelMap model,
			@RequestParam("activateAt") final Calendar activateAt,
			HttpServletRequest request) {
		List<Game> games = gameService.fetchGamesActivatedAt(activateAt,
				countryLanguageHelper.getCurrentCountry(request));

		if (games.isEmpty()) {
			return "redirect:list";
		} else {
			return "redirect:view?id=" + games.get(0).getId();
		}
	}

	@ModelAttribute("bot")
	public BotBean createCommand(final ModelMap model) {
		BotBean bean = new BotBean();
		bean.setBots(userService.fetchAll(RoleEnum.BOT));

		return bean;
	}
}
