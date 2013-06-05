package net.gobro.plauen.web.validator;

import javax.servlet.http.HttpServletRequest;

import net.gobro.plauen.model.Game;
import net.gobro.plauen.model.User;
import net.gobro.plauen.model.UserPlay;
import net.gobro.plauen.service.GameService;
import net.gobro.plauen.service.UserService;
import net.gobro.plauen.web.beans.BotBean;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;


public class BotValidator implements Validator {

	@Autowired
	private UserService userService;

	@Autowired
	private GameService gameService;

	@SuppressWarnings("unchecked")
	@Override
	public boolean supports(final Class c) {
		return c.equals(BotBean.class);
	}

	public void validate(final HttpServletRequest request, Object target,
			Errors errors) {

		validate(target, errors);

		if (errors.hasErrors()) {
			return;
		}
	}

	@Override
	public void validate(Object target, Errors errors) {
		final BotBean command = (BotBean) target;
		String botName = StringUtils.trimToNull(command.getBotName());
		Integer botId = command.getBotId();

		ValidationUtils.rejectIfEmpty(errors, "gameId",
				"error.bot.gameId.empty", "ID of the game is empty!");

		if (errors.hasErrors()) {
			return;
		}

		if (botId == null && botName == null) {
			ValidationUtils
					.rejectIfEmpty(errors, "botName",
							"error.bot.username.empty",
							"Username of new bot is empty!");

			if (errors.hasErrors()) {
				return;
			}
		}

		if (botName != null && userService.isLoginExists(botName)) {
			errors.rejectValue("botName", "error.bot.username.exists",
					"Username choosen for new bot is already registered!");
			return;
		}

		if (botId != null) {
			User bot = userService.fetch(botId);
			Game game = gameService.fetch(command.getGameId());

			if (bot.getUserPlays() != null) {
				for (UserPlay userPlay : bot.getUserPlays()) {
					if (userPlay.getGame().equals(game)) {
						errors.rejectValue("botId", "error.bot.game.joined",
								"Selected bot is already joined the game!");
					}
				}
			}
		}
	}

}
