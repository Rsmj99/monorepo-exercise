package com.exercise.msaccountmovement.exception;

public class FeignCommunicationException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public FeignCommunicationException() {
		super("Error al intentar comunicarse con el otro microservicio");
	}
}
