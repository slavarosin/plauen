package net.gobro.plauen.service;

import java.util.List;

import net.gobro.plauen.model.CountryEnum;
import net.gobro.plauen.model.Winners;

public interface WinnersService {

	List<Winners> fetchAll();

	List<Winners> fetchLast(int number, CountryEnum country);

	List<Winners> findWinners();

	Winners store(Winners obj);
}
