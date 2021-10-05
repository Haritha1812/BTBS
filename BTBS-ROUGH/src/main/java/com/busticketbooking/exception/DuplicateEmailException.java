package com.busticketbooking.exception;

import javax.management.RuntimeErrorException;

public class DuplicateEmailException extends RuntimeException {

	public DuplicateEmailException(String str) {
		super(str);
	}
}
