package net.gobro.plauen.web.spring;

import java.beans.PropertyEditorSupport;

import net.gobro.plauen.model.StatusEnum;


public class SmsStatusEnumEditor extends PropertyEditorSupport {

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		setValue(StatusEnum.valueOf(text.toUpperCase()));
	}

}
