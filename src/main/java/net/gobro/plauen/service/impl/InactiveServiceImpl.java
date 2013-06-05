package net.gobro.plauen.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.gobro.plauen.dao.UserPlayDao;
import net.gobro.plauen.model.CountryEnum;
import net.gobro.plauen.model.Game;
import net.gobro.plauen.model.Sms;
import net.gobro.plauen.model.UserPlay;
import net.gobro.plauen.service.GameService;
import net.gobro.plauen.service.InactiveService;
import net.gobro.plauen.service.ScoreService;
import net.gobro.plauen.service.SmsService;
import net.gobro.plauen.service.SupportedLanguagesService;
import net.gobro.plauen.web.beans.UserPoints;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class InactiveServiceImpl implements InactiveService {
	private static final Logger LOG = LoggerFactory
			.getLogger(InactiveServiceImpl.class);

	private ScoreService scoreService;

	@Autowired
	private SmsService smsService;

	@Autowired
	private GameService gameService;

	@Autowired
	private SupportedLanguagesService supportedLanguagesService;

	@Autowired
	private UserPlayDao userPlayDao;

	public GameService getGameService() {
		return gameService;
	}

	public ScoreService getScoreService() {
		return scoreService;
	}

	public SmsService getSmsService() {
		return smsService;
	}

	@Override
	public void removeInactivePlayers() {
		List<CountryEnum> countries = supportedLanguagesService.getCountries();
		for (CountryEnum countryEnum : countries) {
			List<Game> games = getGameService().fetchGamesOpenedForBidding(
					countryEnum, Calendar.getInstance());

			for (Game game : games) {
				long days = (((Calendar.getInstance().getTimeInMillis() - game
						.getStartedAt().getTimeInMillis()) / 1000) / 3600) / 24;

				if (days < 2)
					continue;

				Map<UserPlay, UserPoints> userPlays = new HashMap<UserPlay, UserPoints>();
				List<UserPlay> players = game.getUserPlays();
				Iterator<UserPlay> iter = players.iterator();
				while (iter.hasNext()) {
					UserPlay userPlay = iter.next();
					if (!userPlay.isDisabled()) {
						UserPoints points = new UserPoints();
						points.setLastPoints(game.getStartedAt());
						points.setUserPlay(userPlay);
						userPlays.put(userPlay, points);
					}
				}

				// fetch sent sms
				List<Sms> smsList = getSmsService().fetchAll(game.getId());

				for (Sms sms : smsList) {
					if (sms.isDisabled())
						continue;
					UserPlay userPlay = sms.getUserPlay();
					UserPoints points = userPlays.get(userPlay);

					if (points == null) {
						points = new UserPoints();
						points.setLastPoints(game.getStartedAt());
						points.setUserPlay(userPlay);
					}

					Integer smsPoints = getScoreService().getScore(sms);

					if (smsPoints != null) {
						points.addPoints(smsPoints, sms.getTimestamp());
						points.getSms().add(sms);
					}

					userPlays.put(userPlay, points);
				}

				List<UserPoints> points = new ArrayList<UserPoints>(userPlays
						.values());

				for (UserPoints userPoints : points) {
					UserPlay userPlay = userPoints.getUserPlay();

					long daysWithNoPoints = (((Calendar.getInstance()
							.getTimeInMillis() - userPoints.getLastPoints()
							.getTimeInMillis()) / 1000) / 3600) / 24;

					if (daysWithNoPoints >= 2) {
						userPlay.setDisabled(true);

						for (Sms sms : userPoints.getSms()) {
							sms.setDisabled(true);
							getSmsService().store(sms, false);
						}

						userPlayDao.store(userPlay);

						if (LOG.isInfoEnabled()) {
							LOG
									.info("UserPlay ["
											+ userPlay
											+ "] disabled due the inactivity in last period.");
						}
					}
				}
			}
		}
	}

	@Override
	public void removeLastPlayers() {
		List<CountryEnum> countries = supportedLanguagesService.getCountries();
		for (CountryEnum countryEnum : countries) {
			List<Game> games = getGameService().fetchGamesOpenedForBidding(
					countryEnum, Calendar.getInstance());

			for (Game game : games) {
				Map<UserPlay, UserPoints> userPlays = new HashMap<UserPlay, UserPoints>();
				List<UserPlay> players = game.getUserPlays();
				Iterator<UserPlay> iter = players.iterator();
				while (iter.hasNext()) {
					UserPlay userPlay = iter.next();
					if (!userPlay.isDisabled()) {
						UserPoints points = new UserPoints();
						points.setPoints(0);
						points.setUserPlay(userPlay);
						userPlays.put(userPlay, points);
					}
				}

				// fetch sent sms
				List<Sms> smsList = getSmsService().fetchAll(game.getId());

				for (Sms sms : smsList) {
					UserPlay userPlay = sms.getUserPlay();
					UserPoints points = userPlays.get(userPlay);

					if (points == null) {
						points = new UserPoints();
						points.setPoints(0);
						points.setUserPlay(userPlay);
					}

					Integer smsPoints = getScoreService().getScore(sms);

					if (smsPoints != null) {
						points.addPoints(smsPoints, sms.getTimestamp());
					}

					userPlays.put(userPlay, points);
				}

				List<UserPoints> points = new ArrayList<UserPoints>(userPlays
						.values());

				// sort rows by points
				Collections.sort(points, new Comparator<UserPoints>() {
					@Override
					public int compare(UserPoints o1, UserPoints o2) {
						if (o2.getPoints().equals(o1.getPoints())) {
							if (o1.getLastPoints() == null
									& o2.getLastPoints() == null)
								return 0;

							if (o1.getLastPoints() == null)
								return 1;
							if (o2.getLastPoints() == null)
								return -1;

							return o2.getLastPoints().compareTo(
									o1.getLastPoints());
						}
						return o2.getPoints().compareTo(o1.getPoints());
					}
				});

				long days = (((game.getFinishedAt().getTimeInMillis() - Calendar
						.getInstance().getTimeInMillis()) / 1000) / 3600) / 24;

				int max = 0;
				List<Integer> maximumNumberOfPlayersList = getGameService()
						.getMaximumNumberOfPlayersList();
				if (days < maximumNumberOfPlayersList.size()) {
					max = maximumNumberOfPlayersList
							.get(maximumNumberOfPlayersList.size() - (int) days
									- 1);
				}

				if (max >= points.size())
					continue;

				for (int i = max; i < points.size(); i++) {
					UserPoints p = points.get(i);
					UserPlay userPlay = p.getUserPlay();
					userPlay.setDisabled(true);
					userPlayDao.store(userPlay);

					if (LOG.isInfoEnabled()) {
						LOG
								.info("UserPlay ["
										+ userPlay
										+ "] disabled due the inactivity in last period.");
					}
				}
			}
		}
	}

	public void setGameService(GameService gameService) {
		this.gameService = gameService;
	}

	public void setScoreService(ScoreService scoreService) {
		this.scoreService = scoreService;
	}

	public void setSmsService(SmsService smsService) {
		this.smsService = smsService;
	}
}
