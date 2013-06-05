package net.gobro.plauen.web.game;

import java.util.Calendar;

import net.gobro.plauen.model.Game;
import net.gobro.plauen.service.GameService;
import net.gobro.plauen.web.security.CurrentUserHelper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/game/join")
public class JoinController {
	@Autowired
	private CurrentUserHelper currentUserHelper;
	@Autowired
	private GameService gameService;

	@RequestMapping(method = RequestMethod.GET, params = { "id" })
	public String joinGame(final ModelMap model,
			@RequestParam("id") final Integer id) {
		Game game = gameService.fetch(id);

		// join the game
		gameService.join(game, currentUserHelper.getCurrentUser(), Calendar
				.getInstance(), true);

		// redirect to view page

		return "redirect:view?id=" + id;
	}

}
