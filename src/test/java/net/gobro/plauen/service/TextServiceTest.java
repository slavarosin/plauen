package net.gobro.plauen.service;

import junit.framework.Assert;

import net.gobro.plauen.model.Text;
import net.gobro.plauen.service.TextService;

import org.junit.Test;
import org.unitils.dbunit.annotation.DataSet;
import org.unitils.spring.annotation.SpringBeanByType;


@DataSet
public class TextServiceTest extends BaseDatabaseTest {
	@SpringBeanByType
	private TextService textService;

	@Test
	public void testFetch() {
		Text obj = textService.fetch(1);
		Assert.assertNotNull(obj);
		Assert.assertEquals(1, obj.getId().intValue());
	}
}
