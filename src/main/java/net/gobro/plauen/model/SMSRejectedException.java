package net.gobro.plauen.model;

public class SMSRejectedException extends RuntimeException {

	private static final long serialVersionUID = 3264059315765746166L;

	public SMSRejectedException(String msg) {
		super(msg);
	}

}
