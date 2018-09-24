package com.freightcom.common.ws.service.user.impl;

public class ValidationException extends RuntimeException {
	private static final long serialVersionUID = -6950863177244518733L;

	public ValidationException(String message) {
		super(message);
	}
}