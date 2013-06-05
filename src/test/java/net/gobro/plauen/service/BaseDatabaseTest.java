package net.gobro.plauen.service;

import org.unitils.UnitilsJUnit4;
import org.unitils.spring.annotation.SpringApplicationContext;

@SpringApplicationContext( { "classpath:test-ds-config.xml",
		"classpath:application-context.xml", "classpath:application-dao.xml",
		"classpath:application-validation.xml", "classpath:application-context-security.xml" })
public abstract class BaseDatabaseTest extends UnitilsJUnit4 {

}
