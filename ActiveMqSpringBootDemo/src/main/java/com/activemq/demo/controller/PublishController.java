package com.activemq.demo.controller;

import java.util.stream.Collectors;
import org.everit.json.schema.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.activemq.demo.exceptions.InvalidInputException;
import com.activemq.demo.model.ArticleDto;
import com.activemq.demo.model.CustomSuccessResponse;
import com.activemq.demo.service.PublishService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * This class is used to expose the rest endpoint for publishing an article.
 * 
 * @author Ashwini.Sanas
 *
 */
@RequestMapping("/publisher")
@RestController
public class PublishController {

	@Autowired
	PublishService publishService;

	@Autowired
	ObjectMapper om;

	@Autowired
	CustomSuccessResponse response;

	/**
	 * 
	 * @param article -This is ArticleDto object
	 * @return This return statement gives a method call to the publishArticle
	 *         method of PublishService class.
	 * @throws InvalidInputException
	 * @throws JsonProcessingException
	 */
	@PostMapping("/articles")
	public ResponseEntity<String> publishArticle(@RequestBody ArticleDto articleDto)
			throws InvalidInputException, JsonProcessingException {
		try {

			publishService.publishArticle(articleDto);
			String responseJson = om.writerWithDefaultPrettyPrinter().writeValueAsString(response);

			return new ResponseEntity<>(responseJson, HttpStatus.OK);
			
		} catch (ValidationException e) {
			String errors = e.getCausingExceptions()
					.stream().map(ValidationException::getMessage)
					.collect(Collectors.joining(","));
			
			throw new InvalidInputException(errors, e);
		}
	}

}
