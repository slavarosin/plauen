package net.gobro.plauen.web.image;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.gobro.plauen.model.Game;
import net.gobro.plauen.model.Image;
import net.gobro.plauen.service.GameService;
import net.gobro.plauen.service.ImageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/image/delete")
public class DeleteController {
	@Autowired
	private GameService gameService;

	public void setGameService(GameService gameService) {
		this.gameService = gameService;
	}

	public GameService getGameService() {
		return gameService;
	}

	@Autowired
	private ImageService imageService;

	public void setImageService(ImageService imageService) {
		this.imageService = imageService;
	}

	public ImageService getImageService() {
		return imageService;
	}

	@RequestMapping(method = RequestMethod.GET, params = { "gameId", "id" })
	public String view(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam("gameId") final Integer gameId,
			@RequestParam("id") final Integer id) {
		Image imageObj = getImageService().fetchObject(id);

		// remove image from Game object
		Game game = getGameService().fetch(gameId);
		game.getPresent().getImages().remove(imageObj);
		gameService.store(game);

		// remove image from database
		getImageService().remove(id);

		return "redirect:manage?gameId=" + gameId;
	}
}
