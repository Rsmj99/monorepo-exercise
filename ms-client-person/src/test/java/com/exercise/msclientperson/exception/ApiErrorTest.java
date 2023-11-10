package com.exercise.msclientperson.exception;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApiErrorTest {

	@Test
	public void testApiErrorWithList() {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		String message = "Validation Errors";
		List<String> errors = Arrays.asList("Error 1", "Error 2");

		ApiError apiError = new ApiError();
		apiError.setStatus(status);
		apiError.setMessage(message);
		apiError.setErrors(errors);

		assertEquals(status, apiError.getStatus());
		assertEquals(message, apiError.getMessage());
		assertEquals(errors, apiError.getErrors());
	}

	@Test
	public void testApiErrorWithSingleError() {
		HttpStatus status = HttpStatus.NOT_FOUND;
		String message = "Resource Not Found";
		String error = "Resource ID 123 not found";

		ApiError apiError = new ApiError(status, message, error);

		assertEquals(status, apiError.getStatus());
		assertEquals(message, apiError.getMessage());
		assertEquals(Arrays.asList(error), apiError.getErrors());
	}

}
