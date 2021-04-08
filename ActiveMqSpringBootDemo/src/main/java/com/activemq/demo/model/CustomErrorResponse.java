package com.activemq.demo.model;

import org.springframework.stereotype.Component;
import lombok.Getter;
import lombok.Setter;

/**
 * This class declares fields for error response body
 * 
 * @author Ashwini.Sanas
 *
 */

@Component
@Getter
@Setter
public class CustomErrorResponse {

	// @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
	// private LocalDateTime timestamp;
	private int status;
	private String error;

}
