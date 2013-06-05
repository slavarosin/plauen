package net.gobro.plauen.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.gobro.plauen.model.Chart;
import net.gobro.plauen.model.ChartRow;
import net.gobro.plauen.model.Game;
import net.gobro.plauen.model.RoleEnum;
import net.gobro.plauen.model.Sms;
import net.gobro.plauen.model.UserPlay;
import net.gobro.plauen.service.ChartService;
import net.gobro.plauen.service.GameService;
import net.gobro.plauen.service.ScoreService;
import net.gobro.plauen.service.SmsService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;


public class ChartServiceImpl implements ChartService {
	private static final Logger LOG = LoggerFactory
			.getLogger(ChartServiceImpl.class);

	@Autowired
	private GameService gameService;

	private ScoreService scoreService;

	@Autowired
	private SmsService smsService;

	@Override
	public Chart fetchChart(Integer gameId) {
		Map<UserPlay, Integer> userPlays = new HashMap<UserPlay, Integer>();

		// fetch users registered in the game
		Game game = gameService.fetch(gameId);

		if (game.getUserPlays() != null) {
			for (UserPlay userPlay : game.getUserPlays()) {
				if (!userPlay.isDisabled()) {
					userPlays.put(userPlay, new Integer(0));
				} else {
					if (LOG.isDebugEnabled()) {
						LOG.debug("UserPlay [" + userPlay
								+ "] ignored in chart, because it is disabled");
					}
				}
			}
		}

		// fetch sent sms
		List<Sms> smsList = smsService.fetchAll(gameId);

		for (Sms sms : smsList) {
			UserPlay userPlay = sms.getUserPlay();
			Integer points = userPlays.get(userPlay);

			if (points == null) {
				points = 0;
			}

			Integer smsPoints = getScoreService().getScore(sms);

			if (smsPoints != null) {
				points += smsPoints;
			}

			userPlays.put(userPlay, points);
		}

		// convert map to chart rows
		List<ChartRow> rows = new ArrayList<ChartRow>();

		for (UserPlay user : userPlays.keySet()) {
			ChartRow row = new ChartRow();
			row.setUsername(user.getUser().getLogin());
			row.setPoints(userPlays.get(user));
			row.setBot(RoleEnum.BOT.equals(user.getUser().getRole()));
			row.setAlias(user.getAlias());
			rows.add(row);
		}

		Chart chart = new Chart();
		chart.setRows(rows);

		// sort rows by points
		Collections.sort(chart.getRows(), new Comparator<ChartRow>() {

			@Override
			public int compare(ChartRow o1, ChartRow o2) {
				return o2.getPoints().compareTo(o1.getPoints());
			}

		});

		return chart;
	}

	public ScoreService getScoreService() {
		return scoreService;
	}

	public void setScoreService(ScoreService scoreService) {
		this.scoreService = scoreService;
	}
}
