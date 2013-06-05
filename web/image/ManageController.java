package net.gobro.plauen.web.image;

import net.gobro.plauen.model.Game;
import net.gobro.plauen.service.GameService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/image/manage")
public class ManageController {
	@Autowired
	private GameService gameService;

	public void setGameService(GameService gameService) {
		this.gameService = gameService;
	}

	public GameService getGameService() {
		return gameService;
	}

	@RequestMapping(method = RequestMethod.GET, params = { "gameId" })
	public String view(final ModelMap model,
			@RequestParam("gameId") final Integer gameId) {
		Game game = getGameService().fetch(gameId);

		model.addAttribute("game", game);

		return "image/manage";
	}

}
