package com.exercise.msclientperson.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.hibernate.exception.ConstraintViolationException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomExceptionHandlerTest {

	@Test
	public void testHandleValidationExceptions() {
		CustomExceptionHandler exceptionHandler = new CustomExceptionHandler();
		BindingResult bindingResult = new BeanPropertyBindingResult(null, "alumno");
		for (FieldError fieldError : List.of(new FieldError("alumno", "nombre", "no debe ser nulo"))) {
			bindingResult.addError(fieldError);
		}
		MethodArgumentNotValidException ex = new MethodArgumentNotValidException(null, bindingResult);

		ApiError response = exceptionHandler.handleValidationExceptions(ex);

		assertEquals(HttpStatus.BAD_REQUEST, response.getStatus());
		assertEquals("Errores de Validación", response.getMessage());
	}

	@Test
	public void testHandleClientNotFoundException() {
		CustomExceptionHandler exceptionHandler = new CustomExceptionHandler();
		ClientNotFoundException ex = new ClientNotFoundException(1L);

		ApiError response = exceptionHandler.handleClientNotFoundException(ex);

		assertEquals(HttpStatus.NOT_FOUND, response.getStatus());
		assertEquals("Error de búsqueda", response.getMessage());
	}

	@Test
	public void testHandleConstraintViolationException() {
		CustomExceptionHandler exceptionHandler = new CustomExceptionHandler();
		ConstraintViolationException ex = new ConstraintViolationException("Message", null, "uk_identification");

		ApiError response = exceptionHandler.handleConstraintViolationException(ex);

		assertEquals(HttpStatus.BAD_REQUEST, response.getStatus());
		assertEquals("Error de restricción única", response.getMessage());
	}

	@Test
	public void testHandleConstraintViolationOtherException() {
		CustomExceptionHandler exceptionHandler = new CustomExceptionHandler();
		ConstraintViolationException ex = new ConstraintViolationException("Message", null, "constraint");

		ApiError response = exceptionHandler.handleConstraintViolationException(ex);

		assertEquals(HttpStatus.BAD_REQUEST, response.getStatus());
		assertEquals("Error con un constraint", response.getMessage());
	}
}
