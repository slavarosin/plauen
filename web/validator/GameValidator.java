package net.gobro.plauen.web.validator;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.gobro.plauen.model.Game;
import net.gobro.plauen.service.GameService;
import net.gobro.plauen.web.beans.CreateGameBean;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;


public class GameValidator implements Validator {

	@Autowired
	private GameService gameService;

	@SuppressWarnings("unchecked")
	@Override
	public boolean supports(Class c) {
		return c.equals(CreateGameBean.class);
	}

	public void validate(HttpServletRequest request, Object target,
			BindingResult errors) {
		validate(target, errors);

		if (errors.hasErrors()) {
			return;
		}
	}

	@Override
	public void validate(Object target, Errors errors) {
		final CreateGameBean command = (CreateGameBean) target;

		ValidationUtils.rejectIfEmpty(errors, "country",
				"error.game.create.country.empty", "Country is empty!");

		if (errors.hasErrors()) {
			return;
		}

		ValidationUtils.rejectIfEmpty(errors, "internalName",
				"error.game.create.internalName.empty",
				"Internal name is empty!");

		if (errors.hasErrors()) {
			return;
		}

		ValidationUtils.rejectIfEmpty(errors, "internalDescription",
				"error.game.create.internalDescription.empty",
				"Internal description is empty!");

		if (errors.hasErrors()) {
			return;
		}

		ValidationUtils.rejectIfEmpty(errors, "activateAt",
				"error.game.create.activateAt.empty",
				"Activation date is empty!");

		if (errors.hasErrors()) {
			return;
		}

		List<Game> gamesActivatedAtSameDate = gameService
				.fetchGamesActivatedAt(command.getActivateAt(), command
						.getCountry());

		if (!gamesActivatedAtSameDate.isEmpty()) {
			boolean showError = true;

			if (command.getId() != null) {
				for (Game game : gamesActivatedAtSameDate) {
					if (game.getId().equals(command.getId())) {
						showError = false;
						break;
					}
				}
			}

			if (showError) {
				errors
						.rejectValue("activateAt",
								"error.game.create.activateAt.exists",
								"There is already game which is activated on same date!");
				return;
			}
		}

		ValidationUtils.rejectIfEmpty(errors, "startedAt",
				"error.game.create.startedAt.empty", "Start date is empty!");

		if (errors.hasErrors()) {
			return;
		}

		List<Game> gamesStartingAtSameDate = gameService.fetchGamesStartingAt(
				command.getStartedAt(), command.getCountry());

		if (!gamesStartingAtSameDate.isEmpty()) {
			boolean showError = true;

			if (command.getId() != null) {
				for (Game game : gamesStartingAtSameDate) {
					if (game.getId().equals(command.getId())) {
						showError = false;
						break;
					}
				}
			}

			if (showError) {
				errors.rejectValue("startedAt",
						"error.game.create.startedAt.exists",
						"There is already game which is started on same date!");
				return;
			}
		}

		if (!command.getActivateAt().before(command.getStartedAt())) {
			errors.rejectValue("activateAt",
					"error.game.create.activateAt.mustBeBeforeStart",
					"Activation date must be before start date!");
		}

		ValidationUtils.rejectIfEmpty(errors, "name",
				"error.game.create.name.empty", "Names are missed!");

		if (errors.hasErrors()) {
			return;
		}

		ValidationUtils.rejectIfEmpty(errors, "description",
				"error.game.create.description.empty",
				"Descriptions are missed!");

		if (errors.hasErrors()) {
			return;
		}

		ValidationUtils.rejectIfEmpty(errors, "price",
				"error.game.create.price.empty", "Price is empty!");

		if (errors.hasErrors()) {
			return;
		}

		for (String name : command.getName()) {
			if (StringUtils.isBlank(name)) {
				errors.rejectValue("name", "error.game.create.name.missed",
						"All names are mandatory!");

				if (errors.hasErrors()) {
					return;
				}
			}
		}

		for (String description : command.getDescription()) {
			if (StringUtils.isBlank(description)) {
				errors.rejectValue("description",
						"error.game.create.description.missed",
						"All descriptions are mandatory!");

				if (errors.hasErrors()) {
					return;
				}
			}
		}

		if (errors.hasErrors()) {
			return;
		}

		if (command.getId() == null) {
			if (((command.getImage1() == null) && (command.getImage2() == null) && (command
					.getImage3() == null))
					|| ((command.getImage1().length == 0)
							&& (command.getImage2().length == 0) && (command
							.getImage3().length == 0))) {
				errors.rejectValue("image1", "error.game.create.image.missed",
						"At least on image must be uploaded!");

				if (errors.hasErrors()) {
					return;
				}
			}
		}
	}
}
