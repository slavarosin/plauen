/**
 * 
 */
package net.gobro.plauen.web.beans;

import net.gobro.plauen.model.LanguageEnum;

public class LanguageBean {
	private LanguageEnum code;
	private String name;

	public LanguageEnum getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

	public void setCode(LanguageEnum code) {
		this.code = code;
	}

	public void setName(String name) {
		this.name = name;
	}
}