package net.gobro.plauen.web.beans;

import java.util.ArrayList;
import java.util.List;

import net.gobro.plauen.model.Game;
import net.gobro.plauen.model.Image;
import net.gobro.plauen.model.LanguageEnum;
import net.gobro.plauen.model.Present;
import net.gobro.plauen.model.Text;
import net.gobro.plauen.model.TextCodeEnum;
import net.gobro.plauen.model.User;
import net.gobro.plauen.model.UserPlay;
import net.gobro.plauen.service.collections.TextPredicate;
import net.gobro.plauen.service.collections.UserPredicate;

import org.apache.commons.beanutils.BeanPredicate;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

public class GameConverter {

	private static final int SHORT_DESCRIPTION_LEN = 700;

	public static GameBean convertToBean(Game game, final User currentUser,
			LanguageEnum currentLanguage, LanguageEnum defaultLanguage) {
		GameBean bean = new GameBean();
		bean.setPrice(game.getPresent().getPrice());
		bean.setRegistrationOpened(game.isRegistrationOpened());
		bean.setGameClosed(game.isGameClosed());
		bean.setBiddingOpened(game.isBiddingOpened());
		bean.setInternalName(game.getPresent().getName());
		bean.setInternalDescription(game.getPresent().getComment());

		Text text = (Text) CollectionUtils.find(game.getPresent().getTexts(),
				new TextPredicate(TextCodeEnum.DESCRIPTION, currentLanguage));

		if (text == null) {
			text = (Text) CollectionUtils
					.find(game.getPresent().getTexts(), new TextPredicate(
							TextCodeEnum.DESCRIPTION, defaultLanguage));
		}

		bean.setDescription(text != null ? text.getText() : null);
		if (bean.getDescription() != null) {
			String shortDescription = bean.getDescription();
			if (shortDescription.length() > SHORT_DESCRIPTION_LEN) {
				shortDescription = shortDescription.substring(0,
						SHORT_DESCRIPTION_LEN);
			}
			bean.setShortDescription(shortDescription);
		}
		bean.setStartedAt(game.getStartedAt());

		text = (Text) CollectionUtils.find(game.getPresent().getTexts(),
				new TextPredicate(TextCodeEnum.NAME, currentLanguage));

		if (text == null) {
			text = (Text) CollectionUtils.find(game.getPresent().getTexts(),
					new TextPredicate(TextCodeEnum.NAME, defaultLanguage));
		}

		bean.setName(text != null ? text.getText() : null);
		UserPlay up = ((UserPlay) CollectionUtils.find(game.getUserPlays(),
				new BeanPredicate("user", new UserPredicate(currentUser))));
		if (up != null && up.isDisabled()) {
			up = null;
		}
		bean.setUserPlay(up);
		bean.setId(game.getId());
		bean.setFinishedAt(game.getFinishedAt());

		bean.setImages(new ArrayList<Integer>());

		if (game.getPresent().getImages() != null) {
			for (Image image : game.getPresent().getImages()) {
				bean.getImages().add(image.getId());
			}
		}

		if (game.getUserPlays() != null) {
			String members = game.getUserPlays().size() + "";
			int disabled = 0;
			List<UserPlay> userPlays = game.getUserPlays();
			for (UserPlay userPlay : userPlays) {
				if (userPlay.isDisabled())
					disabled++;
			}

			if (disabled > 0) {
				members = (game.getUserPlays().size() - disabled) + "/"
						+ members;
			}
			bean.setMembers(members);
		}

		return bean;
	}

	public static List<GameBean> convertToBeans(List<Game> games,
			final User currentUser, LanguageEnum currentLanguage,
			LanguageEnum defaultLanguage) {
		List<GameBean> gameBeans = new ArrayList<GameBean>();

		for (Game game : games) {
			gameBeans.add(convertToBean(game, currentUser, currentLanguage,
					defaultLanguage));
		}

		return gameBeans;
	}

	public static Game convertToModel(CreateGameBean command,
			List<LanguageEnum> languages) {
		Game game = new Game();
		game.setId(command.getId());
		game.setCountry(command.getCountry());
		game.setPresent(new Present());
		game.getPresent().setComment(command.getInternalDescription());
		game.getPresent().setName(command.getInternalName());
		game.getPresent().setPrice(command.getPrice());
		game.getPresent().setTexts(new ArrayList<Text>());

		for (int i = 0; i < command.getName().size(); i++) {
			String name = command.getName().get(i);

			Text text = new Text();
			text.setCode(TextCodeEnum.NAME);
			text.setLanguage(languages.get(i));
			text.setText(filterText(StringUtils.trim(name)));
			text.setPresent(game.getPresent());

			game.getPresent().getTexts().add(text);
		}

		for (int i = 0; i < command.getDescription().size(); i++) {
			String description = command.getDescription().get(i);

			Text text = new Text();
			text.setCode(TextCodeEnum.DESCRIPTION);
			text.setLanguage(languages.get(i));
			text.setText(filterText(StringUtils.trim(description)));
			text.setPresent(game.getPresent());

			game.getPresent().getTexts().add(text);
		}

		game.getPresent().setImages(new ArrayList<Image>());

		Image image;

		image = new Image();
		image.setImage(command.getImage1());
		image.setPresent(game.getPresent());

		game.getPresent().getImages().add(image);

		image = new Image();
		image.setImage(command.getImage2());
		image.setPresent(game.getPresent());

		game.getPresent().getImages().add(image);

		image = new Image();
		image.setImage(command.getImage3());
		image.setPresent(game.getPresent());

		game.getPresent().getImages().add(image);

		game.setActivateAt(command.getActivateAt());
		game.setStartedAt(command.getStartedAt());

		return game;
	}

	private static String filterText(String text) {
		if (text != null) {
			if (text.startsWith("<p>&nbsp;")) {
				text = "<p>" + text.substring("<p>&nbsp;".length());
			} else if (text.startsWith("<p>&#160;")) {
				text = "<p>" + text.substring("<p>&#160;".length());
			}
		}

		return text;
	}
}
