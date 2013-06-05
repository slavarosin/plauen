package net.gobro.plauen.web.spring;

import java.beans.PropertyEditorSupport;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.springframework.util.StringUtils;

public class CustomCalendarEditor extends PropertyEditorSupport {

	private boolean allowEmpty;
	private DateFormat dateFormat;

	public CustomCalendarEditor(SimpleDateFormat dateFormat, boolean allowEmpty) {
		this.dateFormat = dateFormat;
		this.allowEmpty = allowEmpty;
	}

	public String getAsText() {
		Calendar value = (Calendar) getValue();
		return (value != null ? this.getDateFormat().format(value.getTime())
				: "");
	}

	public DateFormat getDateFormat() {
		return dateFormat;
	}

	public boolean isAllowEmpty() {
		return allowEmpty;
	}

	public void setAllowEmpty(boolean allowEmpty) {
		this.allowEmpty = allowEmpty;
	}

	public void setAsText(String text) throws IllegalArgumentException {
		if (this.isAllowEmpty() && !StringUtils.hasText(text)) {
			setValue(null);
		} else {
			try {
				Calendar cal = Calendar.getInstance();
				cal.setTime(this.getDateFormat().parse(text));
				setValue(cal);
			} catch (ParseException ex) {
				throw new IllegalArgumentException();
			}
		}
	}

	public void setDateFormat(DateFormat dateFormat) {
		this.dateFormat = dateFormat;
	}
}