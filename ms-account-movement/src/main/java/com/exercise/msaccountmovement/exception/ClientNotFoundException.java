package com.exercise.msaccountmovement.exception;

public class ClientNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public ClientNotFoundException(Long clientId) {
		super("No existe un cliente con ID: " + clientId);
	}
}
