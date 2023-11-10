package com.exercise.msaccountmovement.exception;

public class AccountNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public AccountNotFoundException(Long accountId) {
		super("No existe una cuenta con ID: " + accountId);
	}
}
