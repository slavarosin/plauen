package net.gobro.plauen.service;

import java.util.List;

import net.gobro.plauen.model.CountryEnum;
import net.gobro.plauen.model.Winners;
import net.gobro.plauen.service.GameService;
import net.gobro.plauen.service.UserService;
import net.gobro.plauen.service.WinnersService;

import org.junit.Assert;
import org.junit.Test;
import org.unitils.dbunit.annotation.DataSet;
import org.unitils.spring.annotation.SpringBeanByType;

@DataSet
public class WinnersServiceTest extends BaseDatabaseTest {
	@SpringBeanByType
	private GameService gameService;
	@SpringBeanByType
	private UserService userService;
	@SpringBeanByType
	private WinnersService winnersService;

	@Test
	public void testFetchAll() {
		List<Winners> list = winnersService.fetchAll();
		Assert.assertNotNull(list);
		Assert.assertEquals(2, list.size());
	}

	@Test
	public void testFetchLast() {
		List<Winners> list = winnersService.fetchLast(2, CountryEnum.EE);
		Assert.assertNotNull(list);
		Assert.assertEquals(2, list.size());
	}

	@Test
	public void testFindWinners() {
		List<Winners> list = winnersService.findWinners();
		Assert.assertNotNull(list);
		Assert.assertEquals(2, list.size());
	}

	@Test
	public void testStore() {
		Winners obj = new Winners();
		obj.setUser(userService.fetch(1));
		obj.setGame(gameService.fetch(1));
		Winners actualObj = winnersService.store(obj);
		Assert.assertNotNull(actualObj);
		Assert.assertNotNull(actualObj.getId());
		Assert.assertNotNull(actualObj.getTimestamp());
		Assert.assertNotNull(actualObj.getUser());
		Assert.assertNotNull(actualObj.getGame());
	}
}
