package net.gobro.plauen.service;

import junit.framework.Assert;

import net.gobro.plauen.service.AliasService;

import org.junit.Test;
import org.unitils.dbunit.annotation.DataSet;
import org.unitils.spring.annotation.SpringBeanByType;


@DataSet
public class AliasServiceTest extends BaseDatabaseTest {
	@SpringBeanByType
	private AliasService aliasService;

	@Test
	public void testFetch() {
		String alias = aliasService.getAlias(1);
		Assert.assertNotNull(alias);
		Assert.assertEquals(6, alias.length());
	}

	@Test
	public void testFetchByCreditionals() {
		aliasService.freeReserves(0L);
	}
}
