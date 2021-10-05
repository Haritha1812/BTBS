package com.busticketbooking.exception;

public class NullValueException extends RuntimeException {

	private static final long serialVersionUID = -550105279094709335L;

	public NullValueException(String msg) {
		super(msg);
	}
}