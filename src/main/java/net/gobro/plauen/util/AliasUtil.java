package net.gobro.plauen.util;

import org.apache.commons.lang.StringUtils;

public class AliasUtil {
	
	private static String[] REPLACES = new String[] {"A", "B", "C", "D", "E", "F", "G", "H", "J", "K"};

	public static String convertNumberToAlias(int length, int number) {
		StringBuffer result = new StringBuffer(StringUtils.leftPad(Integer.toString(number), length, "0"));
		result.reverse();
		int numberToReplace = Integer.parseInt(result.substring(0, 1));
		result.replace(0, 1, REPLACES[numberToReplace]);
		return result.toString();
	}
	
}
