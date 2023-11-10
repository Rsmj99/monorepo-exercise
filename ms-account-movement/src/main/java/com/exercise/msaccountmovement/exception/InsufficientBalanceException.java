package com.exercise.msaccountmovement.exception;

public class InsufficientBalanceException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public InsufficientBalanceException(Double value) {
		super("No se realizó el retiro porque el saldo de la cuenta es menor a: " + value);
	}
}
