package net.gobro.plauen.service;

import java.util.Calendar;
import java.util.List;

import net.gobro.plauen.model.CountryEnum;
import net.gobro.plauen.model.Game;
import net.gobro.plauen.model.User;
import net.gobro.plauen.model.UserPlay;

public interface GameService {

	Game create(Game game);

	Game fetch(Integer id);

	List<Game> fetchGamesOpenedForBidding(CountryEnum country, Calendar baseDate);

	List<Game> fetchAll(CountryEnum attribute);

	// List<Game> fetchGamesOpenedForRegistration(CountryEnum country,
	// Calendar baseDate);

	List<Game> fetchGamesActivatedAt(Calendar date, CountryEnum country);

	List<Game> fetchGamesStartingAt(Calendar date, CountryEnum country);

	UserPlay join(Game game, User bot, Calendar instance, boolean validate);

	Game store(Game game);

	List<Game> fetchClosedGames(Calendar forDate);

	List<Integer> getMaximumNumberOfPlayersList();

}
