package net.gobro.plauen.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import net.gobro.plauen.dao.GameDao;
import net.gobro.plauen.dao.UserPlayDao;
import net.gobro.plauen.dao.criteria.DateRange;
import net.gobro.plauen.dao.criteria.GameCriteria;
import net.gobro.plauen.model.CountryEnum;
import net.gobro.plauen.model.Game;
import net.gobro.plauen.model.Image;
import net.gobro.plauen.model.LanguageEnum;
import net.gobro.plauen.model.Text;
import net.gobro.plauen.model.TextCodeEnum;
import net.gobro.plauen.model.User;
import net.gobro.plauen.model.UserPlay;
import net.gobro.plauen.service.AliasService;
import net.gobro.plauen.service.GameService;
import net.gobro.plauen.service.ScoreService;
import net.gobro.plauen.service.SupportedLanguagesService;
import net.gobro.plauen.service.collections.TextPredicate;
import net.gobro.plauen.service.collections.UserPredicate;

import org.apache.commons.beanutils.BeanPredicate;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public class GameServiceImpl implements GameService {

	private static final Logger LOG = LoggerFactory
			.getLogger(GameServiceImpl.class);

	@Autowired
	private AliasService aliasService;

	@Autowired
	private GameDao gameDao;

	private List<Integer> maximumNumberOfPlayersList;

	private ScoreService scoreService;

	@Autowired
	private SupportedLanguagesService supportedLanguagesService;

	@Autowired
	private UserPlayDao userPlayDao;

	protected void calculateTransientFields(Game game) {
		// registration is opened between game.activateAt and game.startedAt
		// and if number of registered players is not reached the maximum
		boolean registrationOpened = Calendar.getInstance().after(
				game.getActivateAt())
				&& Calendar.getInstance().before(game.getStartedAt());
		registrationOpened = registrationOpened
				&& ((game.getUserPlays() == null) || (game.getUserPlays()
						.size() < getMaximumNumberOfPlayersList().get(0)));
		game.setRegistrationOpened(registrationOpened);

		// bidding is opened between game.startedAt and game.finishedAt
		game.setBiddingOpened(Calendar.getInstance().after(game.getStartedAt())
				&& Calendar.getInstance().before(game.getFinishedAt()));

		// game closed after game.finishedAt
		game.setGameClosed(Calendar.getInstance().after(game.getFinishedAt()));
	}

	@Override
	@Transactional
	public Game create(Game game) {
		// truncate dates
		truncateDates(game);

		// remove empty images
		for (Iterator<Image> iter = game.getPresent().getImages().iterator(); iter
				.hasNext();) {
			Image image = iter.next();

			if ((image == null) || (image.getImage() == null)
					|| (image.getImage().length == 0)) {
				iter.remove();
			}
		}

		// store game
		gameDao.store(game);

		calculateTransientFields(game);

		if (LOG.isInfoEnabled()) {
			LOG.info("Game created: " + game);
		}

		return game;
	}

	@Override
	public Game fetch(Integer id) {
		Game game = getGameDao().fetch(id);

		calculateTransientFields(game);

		return game;
	}

	@Override
	public List<Game> fetchAll(CountryEnum country) {
		GameCriteria criteria = new GameCriteria();
		criteria.setCountry(country);

		List<Game> list = gameDao.find(criteria);

		for (Game game : list) {
			calculateTransientFields(game);
		}

		return list;
	}

	@Override
	public List<Game> fetchClosedGames(Calendar forDate) {
		List<Game> list = gameDao.findClosedGames(forDate);

		for (Game game : list) {
			calculateTransientFields(game);
		}

		return list;
	}

	@Override
	public List<Game> fetchGamesActivatedAt(Calendar date, CountryEnum country) {
		GameCriteria criteria = new GameCriteria();
		criteria.setRegistrationPeriod(new DateRange());
		criteria.getRegistrationPeriod().setStart(
				DateUtils.truncate(date, Calendar.DATE));
		criteria.getRegistrationPeriod().setEnd(
				DateUtils.truncate(date, Calendar.DATE));
		criteria.getRegistrationPeriod().getEnd().add(Calendar.DATE, 1);
		criteria.getRegistrationPeriod().getEnd().add(Calendar.MILLISECOND, -1);
		criteria.setCountry(country);

		List<Game> list = gameDao.find(criteria);

		for (Game game : list) {
			calculateTransientFields(game);
		}

		return list;
	}

	@Override
	public List<Game> fetchGamesOpenedForBidding(CountryEnum country,
			Calendar baseDate) {
		Calendar periodStart = (Calendar) baseDate.clone();
		periodStart.add(Calendar.DATE, -7);

		Calendar periodEnd = (Calendar) baseDate.clone();

		GameCriteria criteria = new GameCriteria();
		criteria.setPlayPeriod(new DateRange());
		criteria.getPlayPeriod().setStart(periodStart);
		criteria.getPlayPeriod().setEnd(periodEnd);
		criteria.setCountry(country);

		List<Game> list = gameDao.find(criteria);

		for (Game game : list) {
			calculateTransientFields(game);
		}

		return list;
	}

	// @Override
	// public List<Game> fetchGamesOpenedForRegistration(CountryEnum country,
	// Calendar baseDate) {
	// Calendar periodStart = (Calendar) baseDate.clone();
	// periodStart.add(Calendar.DATE, -7);
	//
	// Calendar periodEnd = (Calendar) baseDate.clone();
	//
	// GameCriteria criteria = new GameCriteria();
	// criteria.setRegistrationPeriod(new DateRange());
	// criteria.getRegistrationPeriod().setStart(periodStart);
	// criteria.getRegistrationPeriod().setEnd(periodEnd);
	// criteria.setCountry(country);
	//
	// List<Game> list = gameDao.find(criteria);
	//
	// for (Game game : list) {
	// calculateTransientFields(game);
	// }
	//
	// return list;
	// }

	@Override
	public List<Game> fetchGamesStartingAt(Calendar date, CountryEnum country) {
		GameCriteria criteria = new GameCriteria();
		criteria.setPlayPeriod(new DateRange());
		criteria.getPlayPeriod().setStart(
				DateUtils.truncate(date, Calendar.DATE));
		criteria.getPlayPeriod()
				.setEnd(DateUtils.truncate(date, Calendar.DATE));
		criteria.getPlayPeriod().getEnd().add(Calendar.DATE, 1);
		criteria.getPlayPeriod().getEnd().add(Calendar.MILLISECOND, -1);
		criteria.setCountry(country);

		List<Game> list = gameDao.find(criteria);

		for (Game game : list) {
			calculateTransientFields(game);
		}

		return list;
	}

	public GameDao getGameDao() {
		return gameDao;
	}

	public ScoreService getScoreService() {
		return scoreService;
	}

	@Override
	public UserPlay join(Game game, User user, Calendar forDate,
			boolean validate) {
		UserPlay userPlay = null;

		if (!validate || game.isBiddingOpened()) {
			userPlay = ((UserPlay) CollectionUtils.find(game.getUserPlays(),
					new BeanPredicate("user", new UserPredicate(user))));

			if (userPlay == null) {
				userPlay = new UserPlay();
				userPlay.setGame(game);
				userPlay.setUser(user);
				userPlay.setAlias(aliasService.getAlias(game.getId()));
			}

			userPlay.setDisabled(false);

			userPlayDao.store(userPlay);

			if (LOG.isInfoEnabled()) {
				LOG.info("User [" + user + "] has joined the game [" + game
						+ "] with alias [" + userPlay.getAlias() + "]");
			}
		} else {
			if (LOG.isWarnEnabled()) {
				LOG
						.warn("User ["
								+ user
								+ "] request to join the game ["
								+ game
								+ "] was rejected because the game is not opened for registration");
			}
		}

		return userPlay;
	}

	public void setGameDao(GameDao gameDao) {
		this.gameDao = gameDao;
	}

	public void setScoreService(ScoreService scoreService) {
		this.scoreService = scoreService;
	}

	@Override
	public Game store(Game game) {
		if (game.getId() == null) {
			throw new RuntimeException(
					"To create a new game use \"create\" method of this service");
		}

		// truncate dates
		truncateDates(game);

		// check IDs of related objects
		Game originalGame = fetch(game.getId());

		// set ID of the present
		if (game.getPresent().getId() == null) {
			game.getPresent().setId(originalGame.getPresent().getId());
		}

		// set IDs of texts
		List<Text> texts = new LinkedList<Text>();

		// names
		for (LanguageEnum language : supportedLanguagesService
				.getLanguagesFor(game.getCountry())) {
			Text updatedText = (Text) CollectionUtils
					.find(game.getPresent().getTexts(), new TextPredicate(
							TextCodeEnum.NAME, language));
			Text originalText = (Text) CollectionUtils.find(originalGame
					.getPresent().getTexts(), new TextPredicate(
					TextCodeEnum.NAME, language));

			if ((originalText != null) && (updatedText != null)) {
				originalText.setText(updatedText.getText());
				texts.add(originalText);
			} else if (updatedText != null) {
				texts.add(updatedText);
			}
		}

		// description
		for (LanguageEnum language : supportedLanguagesService
				.getLanguagesFor(game.getCountry())) {
			Text updatedText = (Text) CollectionUtils.find(game.getPresent()
					.getTexts(), new TextPredicate(TextCodeEnum.DESCRIPTION,
					language));
			Text originalText = (Text) CollectionUtils.find(originalGame
					.getPresent().getTexts(), new TextPredicate(
					TextCodeEnum.DESCRIPTION, language));

			if ((originalText != null) && (updatedText != null)) {
				originalText.setText(updatedText.getText());
				texts.add(originalText);
			} else if (updatedText != null) {
				texts.add(updatedText);
			}
		}

		game.getPresent().setTexts(texts);

		// set IDs of images
		List<Image> images = new LinkedList<Image>();

		for (int i = 0; i < 3; i++) {
			images.add(null);
		}

		int i = 0;

		for (Image image : originalGame.getPresent().getImages()) {
			images.set(i++, image);
		}

		int k = 0;

		for (Image image : game.getPresent().getImages()) {
			Image originalImage = images.get(k);

			if ((originalImage != null) && (originalImage.getId() != null)
					&& (image.getImage() != null)
					&& (image.getImage().length > 0)) {
				originalImage.setImage(image.getImage());
			} else if ((image.getImage() != null)
					&& (image.getImage().length > 0)) {
				images.set(k, image);
			}

			k++;
		}

		game.getPresent().setImages(images);

		// store game
		gameDao.store(game);

		calculateTransientFields(game);

		if (LOG.isInfoEnabled()) {
			LOG.info("Game stored: " + game);
		}

		return game;
	}

	public void truncateDates(Game game) {
		// truncate activateAt to the noon
		game.setActivateAt(DateUtils.truncate(game.getActivateAt(),
				Calendar.DATE));
		game.getActivateAt().set(Calendar.HOUR_OF_DAY, 20);

		// truncate startedAt to the noon
		game.setStartedAt(DateUtils
				.truncate(game.getStartedAt(), Calendar.DATE));
		game.getStartedAt().set(Calendar.HOUR_OF_DAY, 20);

		// set end of game to the noon 7 days after startedAt
		game.setFinishedAt((Calendar) game.getStartedAt().clone());
		game.getFinishedAt().add(Calendar.DATE, 7);
		game.getFinishedAt().set(Calendar.HOUR_OF_DAY, 20);
	}

	public void setMaximumNumberOfPlayersList(
			List<Integer> maximumNumberOfPlayersList) {
		this.maximumNumberOfPlayersList = maximumNumberOfPlayersList;
	}

	public void setMaximumNumberOfPlayers(String maximumNumberOfPlayers) {
		setMaximumNumberOfPlayersList(new ArrayList<Integer>());
		Scanner scanner = new Scanner(maximumNumberOfPlayers);
		scanner.useDelimiter(",");

		while (scanner.hasNext()) {
			String max = StringUtils.trimToNull(scanner.next());

			if (max != null) {
				getMaximumNumberOfPlayersList().add(Integer.parseInt(max));
			}
		}
	}

	@Override
	public List<Integer> getMaximumNumberOfPlayersList() {
		return maximumNumberOfPlayersList;
	}
}
