package com.exercise.msaccountmovement.exception;

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
		return new ApiError(HttpStatus.BAD_REQUEST, "Validation Errors", errors);
	}

	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(AccountNotFoundException.class)
	public ApiError handleAccountNotFoundException(AccountNotFoundException ex) {
		return new ApiError(HttpStatus.NOT_FOUND, "Error de búsqueda", ex.getMessage());
	}

	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(MovementNotFoundException.class)
	public ApiError handleMovementNotFoundException(MovementNotFoundException ex) {
		return new ApiError(HttpStatus.NOT_FOUND, "Error de búsqueda", ex.getMessage());
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(InsufficientBalanceException.class)
	public ApiError handleInsufficientBalanceException(InsufficientBalanceException ex) {
		return new ApiError(HttpStatus.BAD_REQUEST, "Saldo no disponible", ex.getMessage());
	}

	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(ClientNotFoundException.class)
	public ApiError handleClientNotFoundException(ClientNotFoundException ex) {
		return new ApiError(HttpStatus.NOT_FOUND, "Error de búsqueda", ex.getMessage());
	}

	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(FeignCommunicationException.class)
	public ApiError handleFeignCommunicationException(FeignCommunicationException ex) {
		return new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, "Error de servicios", ex.getMessage());
	}

	@ExceptionHandler(ConstraintViolationException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ApiError handleConstraintViolationException(ConstraintViolationException ex) {
		String constraintName = ex.getConstraintName();
		String type = constraintName.startsWith("uk_") ? "Error de restricción única" : "Error con un constraint";
		String message = constraintName.equals("uk_account_number")
				? "El valor que le pasa al campo accountNumber ya existe"
				: ex.getMessage();

		return new ApiError(HttpStatus.BAD_REQUEST, type,
				"La restricción '" + constraintName + "' fue violada. Detalles: " + message);
	}
}
