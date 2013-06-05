package net.gobro.plauen.util;

import net.gobro.plauen.util.AliasUtil;
import junit.framework.TestCase;


public class AliasUtilTest extends TestCase {

	public void testConvertNumberToAlias() throws Exception {
		assertEquals(AliasUtil.convertNumberToAlias(6, 0), "A00000");
		assertEquals(AliasUtil.convertNumberToAlias(6, 101), "B01000");
		assertEquals(AliasUtil.convertNumberToAlias(6, 111999), "K99111");
	}
}
