package net.gobro.plauen.web.game;

import static net.gobro.plauen.web.beans.GameConverter.convertToModel;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import net.gobro.plauen.model.Game;
import net.gobro.plauen.model.Image;
import net.gobro.plauen.model.LanguageEnum;
import net.gobro.plauen.model.Text;
import net.gobro.plauen.model.TextCodeEnum;
import net.gobro.plauen.service.GameService;
import net.gobro.plauen.service.SupportedLanguagesService;
import net.gobro.plauen.service.collections.TextPredicate;
import net.gobro.plauen.web.CountryLanguageHelper;
import net.gobro.plauen.web.beans.CreateGameBean;
import net.gobro.plauen.web.spring.CustomCalendarEditor;
import net.gobro.plauen.web.validator.GameValidator;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;

@Controller
public class CreateController {

	// private static final Logger LOG = LoggerFactory
	// .getLogger(CreateController.class);

	@Autowired
	private CountryLanguageHelper countryLanguageHelper;

	@Autowired
	private SupportedLanguagesService supportedLanguagesService;

	@Autowired
	private GameService gameService;

	@Autowired
	private GameValidator validator;

	@ModelAttribute("game")
	public CreateGameBean createCommand(HttpServletRequest request) {
		CreateGameBean game;

		if (request.getParameter("id") != null
				&& request.getParameter("id").length() > 0) {
			Game gameObj = gameService.fetch(Integer.parseInt(request
					.getParameter("id")));
			game = new CreateGameBean();
			game.setId(gameObj.getId());
			game.setActivateAt(gameObj.getActivateAt());
			game.setCountry(gameObj.getCountry());
			game.setDescription(new LinkedList<String>());

			for (LanguageEnum language : supportedLanguagesService
					.getLanguagesFor(countryLanguageHelper
							.getCurrentCountry(request))) {
				Text text = (Text) CollectionUtils.find(gameObj.getPresent()
						.getTexts(), new TextPredicate(
						TextCodeEnum.DESCRIPTION, language));

				if (text != null) {
					game.getDescription().add(text.getText());
				}
			}

			game.setImages(new LinkedList<Integer>());

			for (Image image : gameObj.getPresent().getImages()) {
				game.getImages().add(image.getId());
			}

			game.setInternalDescription(gameObj.getPresent().getComment());
			game.setInternalName(gameObj.getPresent().getName());
			game.setName(new LinkedList<String>());

			for (LanguageEnum language : supportedLanguagesService
					.getLanguagesFor(countryLanguageHelper
							.getCurrentCountry(request))) {
				Text text = (Text) CollectionUtils.find(gameObj.getPresent()
						.getTexts(), new TextPredicate(TextCodeEnum.NAME,
						language));

				if (text != null) {
					game.getName().add(text.getText());
				}
			}

			game.setPrice(gameObj.getPresent().getPrice());
			game.setStartedAt(gameObj.getStartedAt());
		} else {
			game = new CreateGameBean();

			// preselect values
			// country
			game.setCountry(countryLanguageHelper.getCurrentCountry(request));

			// activate at
			String activateAtAsString = request.getParameter("activateAt");

			CustomCalendarEditor editor = new CustomCalendarEditor(
					new SimpleDateFormat("dd.MM.yyyy"), true);
			editor.setAsText(activateAtAsString);

			Calendar requestedActivateAt = (Calendar) editor.getValue();

			if (requestedActivateAt != null) {
				game.setActivateAt(requestedActivateAt);
			} else {
				game.setActivateAt(Calendar.getInstance());
				game.getActivateAt().add(Calendar.DATE, 7);
			}

			// start at
			game.setStartedAt((Calendar) game.getActivateAt().clone());
			game.getStartedAt().add(Calendar.DATE, 7);
		}

		// add country and language list
		game.setCountries(countryLanguageHelper.populateCountries());
		game.setLanguages(countryLanguageHelper.populateLanguages(request));

		return game;
	}

	@InitBinder
	public void init(WebDataBinder binder, Locale locale) {
		binder.registerCustomEditor(byte[].class,
				new ByteArrayMultipartFileEditor());
		binder.registerCustomEditor(Calendar.class, new CustomCalendarEditor(
				new SimpleDateFormat("dd.MM.yyyy"), true));
	}

	@ModelAttribute("image")
	public byte[] populateImageList() {
		return new byte[3];
	}

	@RequestMapping(value = "/game/create", method = RequestMethod.POST, params = "!id")
	public String processCreateSubmit(final HttpServletRequest request,
			@ModelAttribute("game") final CreateGameBean command,
			final BindingResult errors) {
		// set current country
		command.setCountry(countryLanguageHelper.getCurrentCountry(request));

		validator.validate(request, command, errors);

		if (errors.hasErrors()) {
			return "game/create";
		}

		Game game = gameService.create(convertToModel(command,
				supportedLanguagesService.getLanguagesFor(countryLanguageHelper
						.getCurrentCountry(request))));

		return "redirect:view?id=" + game.getId();
	}

	@RequestMapping(value = "/game/edit", method = RequestMethod.POST, params = "id")
	public String processEditSubmit(final HttpServletRequest request,
			@ModelAttribute("game") final CreateGameBean command,
			final BindingResult errors) {
		// set current country
		command.setCountry(countryLanguageHelper.getCurrentCountry(request));

		validator.validate(request, command, errors);

		if (errors.hasErrors()) {
			return "game/edit";
		}

		Game game = gameService.store(convertToModel(command,
				supportedLanguagesService.getLanguagesFor(countryLanguageHelper
						.getCurrentCountry(request))));

		return "redirect:view?id=" + game.getId();
	}

	@RequestMapping(value = "/game/create", method = RequestMethod.GET, params = "!id")
	public String setupCreateForm(final ModelMap model,
			HttpServletRequest request) {
		return "game/create";
	}

	@RequestMapping(value = "/game/edit", method = RequestMethod.GET, params = "id")
	public String setupEditForm(final ModelMap model, HttpServletRequest request) {
		return "game/edit";
	}
}
