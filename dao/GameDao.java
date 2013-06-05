package net.gobro.plauen.dao;

import java.util.Calendar;
import java.util.List;

import net.gobro.plauen.dao.criteria.GameCriteria;
import net.gobro.plauen.model.Game;


public interface GameDao extends GenericDAO<Game, Integer> {

	List<Game> find(GameCriteria searchCriteria);

	List<Game> findClosedGames(Calendar forDate);

}
