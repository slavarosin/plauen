package net.gobro.plauen.dao;

import java.util.List;

import net.gobro.plauen.model.CountryEnum;
import net.gobro.plauen.model.Winners;

public interface WinnersDao extends GenericDAO<Winners, Integer> {

	List<Winners> fetchList(int number, CountryEnum country);

	List<Winners> fetchAll();

}
