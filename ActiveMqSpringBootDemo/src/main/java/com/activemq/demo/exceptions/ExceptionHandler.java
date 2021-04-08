package com.activemq.demo.exceptions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import com.activemq.demo.model.CustomErrorResponse;

/**
 * This class is used to handle custom exceptions
 * 
 * @author Ashwini.Sanas
 *
 */

@ControllerAdvice
public class ExceptionHandler {

	@Autowired
	CustomErrorResponse errors;

	/**
	 * 
	 * @param ex-This is an exception object used to get the exception message
	 * @param request
	 * @return This return statement returns the error response
	 */

	/**
	 * 
	 * @param ex -This is an exception object used to get the exception message
	 * @param request
	 * @return This return statement returns the error response
	 */
	@org.springframework.web.bind.annotation.ExceptionHandler(ArticleIdNotFoundException.class)
	public ResponseEntity<CustomErrorResponse> idNotFound(ArticleIdNotFoundException ex) {

		errors.setError(ex.getMessage());
		errors.setStatus(HttpStatus.NOT_FOUND.value());

		return new ResponseEntity<>(errors, HttpStatus.NOT_FOUND);
	}

	/**
	 * 
	 * @param ex -This is an exception object used to get the exception message
	 * @param request
	 * @return This return statement returns the error response
	 */

	@org.springframework.web.bind.annotation.ExceptionHandler(InvalidInputException.class)
	public ResponseEntity<CustomErrorResponse> invalidInput(InvalidInputException ex) {

		errors.setError(ex.getMessage());
		errors.setStatus(HttpStatus.BAD_REQUEST.value());

		return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);

	}
}