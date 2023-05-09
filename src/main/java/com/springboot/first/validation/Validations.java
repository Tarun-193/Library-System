package com.springboot.first.validation;

import java.sql.SQLIntegrityConstraintViolationException;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//import com.springboot.first.exception.APIException;
//import com.springboot.first.exception.ResoureNotFoundException;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice
public class Validations {

	@ResponseStatus(HttpStatus.BAD_REQUEST)

	@ExceptionHandler(MethodArgumentNotValidException.class)

	public Map<String, String> handleInvalidArgument(MethodArgumentNotValidException ex) {

		Map<String, String> errorMap = new HashMap<>();

		ex.getBindingResult().getFieldErrors().forEach(error -> {

			errorMap.put(error.getField(), error.getDefaultMessage());

		});

		return errorMap;

	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(ConstraintViolationException.class)
	public Map<String, String> handleConstraintViolation(ConstraintViolationException ex) {
		Map<String, String> errorMap = new HashMap<>();
		Set<ConstraintViolation<?>> violations = ex.getConstraintViolations();
		violations.forEach(violation -> {
			String propertyPath = violation.getPropertyPath().toString();
			String message = violation.getMessage();
			errorMap.put(propertyPath, message);
		});
		return errorMap;
	}

//	InternalAuthenticationServiceException
	@ResponseStatus(HttpStatus.BAD_REQUEST)

	@ExceptionHandler(InternalAuthenticationServiceException.class)

	public Map<String, String> handleInvalidArgument(InternalAuthenticationServiceException ex) {

		Map<String, String> errorMap = new HashMap<>();

		errorMap.put("Error Message", ex.getLocalizedMessage());

		return errorMap;

	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)

	@ExceptionHandler(SQLIntegrityConstraintViolationException.class)

	public Map<String, String> handleInvalidArgument(SQLIntegrityConstraintViolationException ex) {

		Map<String, String> errorMap = new HashMap<>();

		errorMap.put("Error Message", ex.getLocalizedMessage());

		return errorMap;

	}
	
//	@ResponseStatus(HttpStatus.BAD_REQUEST)
//	
//	@ExceptionHandler(APIException.class)
//	
//	public Map<String, String> handleInvalidArgument(APIException ex) {
//		
//		Map<String, String> errorMap = new HashMap<>();
//		
//		errorMap.put("Error Message", ex.getLocalizedMessage());
//		
//		return errorMap;
//		
//	}
	

}
