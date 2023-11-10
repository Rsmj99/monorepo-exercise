package com.exercise.msaccountmovement.exception;

public class MovementNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public MovementNotFoundException(Long movementId) {
		super("No existe un movimiento con ID: " + movementId);
	}
}
