package net.gobro.plauen.dao.criteria;

import java.util.Calendar;

public class DateRange {
	private Calendar end;
	private Calendar start;

	public Calendar getEnd() {
		return end;
	}

	public Calendar getStart() {
		return start;
	}

	public void setEnd(Calendar end) {
		this.end = end;
	}

	public void setStart(Calendar start) {
		this.start = start;
	}

}
