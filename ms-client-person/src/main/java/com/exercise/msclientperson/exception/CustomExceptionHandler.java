package com.exercise.msclientperson.exception;

import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomExceptionHandler {

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ApiError handleValidationExceptions(MethodArgumentNotValidException ex) {
		List<String> errors = ex.getBindingResult().getAllErrors().stream()
				.map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.toList());
		return new ApiError(HttpStatus.BAD_REQUEST, "Errores de Validación", errors);
	}

	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(ClientNotFoundException.class)
	public ApiError handleClientNotFoundException(ClientNotFoundException ex) {
		return new ApiError(HttpStatus.NOT_FOUND, "Error de búsqueda", ex.getMessage());
	}

	@ExceptionHandler(ConstraintViolationException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ApiError handleConstraintViolationException(ConstraintViolationException ex) {
		String constraintName = ex.getConstraintName();
		String type = constraintName.startsWith("uk_") ? "Error de restricción única" : "Error con un constraint";
		String message = ex.getMessage();

		return new ApiError(HttpStatus.BAD_REQUEST, type,
				"La restricción '" + constraintName + "' fue violada. Detalles: " + message);
	}
}
