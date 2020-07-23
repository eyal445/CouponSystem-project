package com.coupon_system.exceptions;

public class NotAllowdException extends Exception {

	public NotAllowdException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "NotAllowdException [getMessage()=" + getMessage() + "]";
	}

}
