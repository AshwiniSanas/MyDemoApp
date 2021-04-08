package com.activemq.demo.model;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import lombok.Getter;

/**
 * This class declares fields for successful request response body
 * 
 * @author Ashwini.Sanas
 *
 */

@Component
@Getter
public class CustomSuccessResponse {

	private int status = HttpStatus.OK.value();
	private String message = "success";

}
