package com.coupon_system.exceptions;

public class purchaseCouponsException extends Exception {

	public purchaseCouponsException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "purchaseCouponsException [getMessage()=" + getMessage() + "]";
	}

}
