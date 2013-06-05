/**
 *
 */
package net.gobro.plauen.web.beans;

import net.gobro.plauen.model.CountryEnum;

public class CountryBean {

	private CountryEnum code;

	private String name;

	public CountryEnum getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

	public void setCode(CountryEnum code) {
		this.code = code;
	}

	public void setName(String name) {
		this.name = name;
	}
}