package com.coupon_system.exceptions;

public class AlreadyExsistException extends Exception {

	public AlreadyExsistException(String message) {
		super(message);
		
	}

	@Override
	public String toString() {
		return "AlreadyExsistException [getMessage()=" + getMessage() + "]";
	}

}
