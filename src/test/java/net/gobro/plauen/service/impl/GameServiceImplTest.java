package net.gobro.plauen.service.impl;

import java.util.Calendar;

import junit.framework.Assert;

import net.gobro.plauen.model.Game;
import net.gobro.plauen.service.impl.GameServiceImpl;

import org.junit.Before;
import org.junit.Test;


public class GameServiceImplTest {
	private GameServiceImpl service;

	@Test
	public void testTruncateDates() {
		Game game = new Game();
		game.setActivateAt(Calendar.getInstance());
		game.setStartedAt(Calendar.getInstance());

		service.truncateDates(game);

		Assert.assertEquals(20, game.getActivateAt().get(Calendar.HOUR_OF_DAY));
		Assert.assertEquals(0, game.getActivateAt().get(Calendar.MINUTE));
		Assert.assertEquals(0, game.getActivateAt().get(Calendar.SECOND));

		Assert.assertEquals(20, game.getStartedAt().get(Calendar.HOUR_OF_DAY));
		Assert.assertEquals(0, game.getStartedAt().get(Calendar.MINUTE));
		Assert.assertEquals(0, game.getStartedAt().get(Calendar.SECOND));

		Assert.assertEquals(20, game.getFinishedAt().get(Calendar.HOUR_OF_DAY));
		Assert.assertEquals(0, game.getFinishedAt().get(Calendar.MINUTE));
		Assert.assertEquals(0, game.getFinishedAt().get(Calendar.SECOND));
	}

	@Before
	public void setUp() {
		service = new GameServiceImpl();
	}
}
