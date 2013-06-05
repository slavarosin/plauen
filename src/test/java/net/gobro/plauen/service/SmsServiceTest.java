package net.gobro.plauen.service;

import java.math.BigDecimal;

import junit.framework.Assert;

import net.gobro.plauen.model.CountryEnum;
import net.gobro.plauen.model.CurrencyEnum;
import net.gobro.plauen.model.Sms;
import net.gobro.plauen.model.StatusEnum;
import net.gobro.plauen.service.SmsService;

import org.junit.Test;
import org.unitils.dbunit.annotation.DataSet;
import org.unitils.spring.annotation.SpringBeanByType;


@DataSet
public class SmsServiceTest extends BaseDatabaseTest {
	@SpringBeanByType
	private SmsService smsService;

	public void testFetch() {
		Sms actualObj = smsService.fetch(1);
		Assert.assertNotNull(actualObj);
		Assert.assertEquals(1, actualObj.getId().intValue());
	}

	@Test
	public void testStore() {
		Sms sms = new Sms();
		sms.setCountry(CountryEnum.EE);
		sms.setCurrency(CurrencyEnum.EEK);
		sms.setKeyword("GAME");
		sms.setMessage("123456");
		sms.setMessageId("2");
		sms.setPrice(new BigDecimal(666));
		sms.setSender("3725131010");
		sms.setServiceId("GOBRO");
		sms.setShortCode("1770");
		sms.setStatus(StatusEnum.OK);

		Sms actualObj = smsService.store(sms, false);
		Assert.assertNotNull(actualObj.getId());
		Assert.assertNotNull(actualObj.getUserPlay());
	}
}
