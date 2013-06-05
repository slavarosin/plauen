package net.gobro.plauen.service;

import junit.framework.Assert;

import net.gobro.plauen.model.CountryEnum;
import net.gobro.plauen.model.RoleEnum;
import net.gobro.plauen.model.User;
import net.gobro.plauen.service.UserService;

import org.junit.Test;
import org.unitils.dbunit.annotation.DataSet;
import org.unitils.spring.annotation.SpringBeanByType;


@DataSet
public class UserServiceTest extends BaseDatabaseTest {
	@SpringBeanByType
	private UserService userService;

	@Test
	public void store() {
		User obj = new User();
		obj.setEmail("test@dobro.net");
		obj.setFirstName("test");
		obj.setLastName("test");
		obj.setLogin("login");
		obj.setPasswd("password");
		obj.setPhoneNumber("1188");
		obj.setRole(RoleEnum.ADMIN);
		obj.setUserPlays(null);
		obj.setAddress("address");
		obj.setCity("city");
		obj.setZip("zip");
		obj.setCountry(CountryEnum.EE);

		User actualObj = userService.store(obj);
		Assert.assertNotNull(actualObj);
	}

	@Test
	public void testFetch() {
		User obj = userService.fetch(1);
		Assert.assertNotNull(obj);
		Assert.assertEquals(1, obj.getId().intValue());
		Assert.assertNotNull(obj.getEmail());
		Assert.assertNotNull(obj.getFirstName());
		Assert.assertNotNull(obj.getLastName());
		Assert.assertNotNull(obj.getLogin());
		Assert.assertNotNull(obj.getPasswd());
		Assert.assertNotNull(obj.getPhoneNumber());
		Assert.assertNotNull(obj.getRole());
		Assert.assertEquals(RoleEnum.ADMIN, obj.getRole());
	}
}
