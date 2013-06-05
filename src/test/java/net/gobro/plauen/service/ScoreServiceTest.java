package net.gobro.plauen.service;

import junit.framework.Assert;

import net.gobro.plauen.service.ScoreService;
import net.gobro.plauen.service.SmsService;

import org.junit.Test;
import org.unitils.dbunit.annotation.DataSet;
import org.unitils.spring.annotation.SpringBeanByName;
import org.unitils.spring.annotation.SpringBeanByType;


@DataSet
public class ScoreServiceTest extends BaseDatabaseTest {
	@SpringBeanByName
	private ScoreService scoreService;
	@SpringBeanByType
	private SmsService smsService;

	@Test
	public void testScoreByNull() {
		Integer score = scoreService.getScore(null);
		Assert.assertNull(score);
	}

	@Test
	public void testScoreByPrice() {
		Assert.assertEquals(5, scoreService.getScore(smsService.fetch(1))
				.intValue());
		Assert.assertEquals(12, scoreService.getScore(smsService.fetch(2))
				.intValue());
		Assert.assertEquals(20, scoreService.getScore(smsService.fetch(3))
				.intValue());
		Assert.assertEquals(50, scoreService.getScore(smsService.fetch(4))
				.intValue());
	}

	@Test
	public void testScoreByKeyword() {
		Assert.assertEquals(5, scoreService.getScore(smsService.fetch(5))
				.intValue());
		Assert.assertEquals(12, scoreService.getScore(smsService.fetch(6))
				.intValue());
		Assert.assertEquals(20, scoreService.getScore(smsService.fetch(7))
				.intValue());
		Assert.assertEquals(50, scoreService.getScore(smsService.fetch(8))
				.intValue());
	}
}
