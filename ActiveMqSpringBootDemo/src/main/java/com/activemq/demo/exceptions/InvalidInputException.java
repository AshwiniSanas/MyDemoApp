package com.activemq.demo.exceptions;

import org.everit.json.schema.ValidationException;

/**
 * This class displays the error response when InvalidInputException is thrown.
 * 
 * @author Ashwini.Sanas
 *
 */
public class InvalidInputException extends Exception {

	public InvalidInputException(String errors, ValidationException e) {
		super(e.getMessage() + " " + errors);
	}

}
