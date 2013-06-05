package net.gobro.plauen.service.impl;

import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import net.gobro.plauen.dao.WinnersDao;
import net.gobro.plauen.model.Chart;
import net.gobro.plauen.model.ChartRow;
import net.gobro.plauen.model.CountryEnum;
import net.gobro.plauen.model.Game;
import net.gobro.plauen.model.RoleEnum;
import net.gobro.plauen.model.User;
import net.gobro.plauen.model.Winners;
import net.gobro.plauen.service.ChartService;
import net.gobro.plauen.service.GameService;
import net.gobro.plauen.service.NotificationService;
import net.gobro.plauen.service.UserService;
import net.gobro.plauen.service.WinnersService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class WinnersServiceImpl implements WinnersService {
	private static final Logger LOG = LoggerFactory
			.getLogger(WinnersServiceImpl.class);
	@Autowired
	private ChartService chartService;

	@Autowired
	private GameService gameService;

	@Autowired
	private UserService userService;

	@Autowired
	private WinnersDao winnersDao;

	@Autowired
	private NotificationService notificationService;

	@Override
	public List<Winners> fetchAll() {
		return winnersDao.fetchAll();
	}

	@Override
	public List<Winners> fetchLast(int number, CountryEnum country) {
		return winnersDao.fetchList(number, country);
	}

	@Override
	public List<Winners> findWinners() {
		try {
			List<Game> games = gameService.fetchClosedGames(Calendar
					.getInstance());
			List<Winners> winners = fetchAll();

			// leave only games without winner
			for (Winners winner : winners) {
				for (Iterator<Game> iter = games.iterator(); iter.hasNext();) {
					Game game = iter.next();

					if (winner.getGame().equals(game)) {
						iter.remove();
					}
				}
			}

			// find winners
			for (Game game : games) {
				Chart chart = chartService.fetchChart(game.getId());

				if (chart.getRows().size() > 0) {
					ChartRow firstRow = chart.getRows().get(0);

					Winners winner = new Winners();
					winner.setGame(game);

					User user = userService.fetch(firstRow.getUsername());
					winner.setUser(user);

					store(winner);

					if ((user.getRole() == RoleEnum.USER) & false) { // FIXME:
																		// remove
																		// when
																		// first
																		// winner
																		// comes
						notificationService.notifyUser(user, "You are Winner!",
								"");
					}
				}
			}

			return winners;
		} catch (Exception e) {
			LOG.error("Error while finding winners", e);
			return null;
		}
	}

	@Override
	public Winners store(Winners obj) {
		if (obj.getId() == null) {
			obj.setTimestamp(Calendar.getInstance());
		}

		winnersDao.store(obj);

		if (LOG.isInfoEnabled()) {
			LOG.info("Winner stored: " + obj);
		}

		return obj;
	}
}
