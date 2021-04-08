package com.activemq.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import com.activemq.demo.SchemaValidator;
import com.activemq.demo.exceptions.InvalidInputException;
import com.activemq.demo.model.Article;
import com.activemq.demo.model.ArticleDto;
import com.fasterxml.jackson.core.JsonProcessingException;

/**
 * This class is used to return the response for a valid/invalid post request.
 * 
 * @author Ashwini.Sanas
 *
 */
@Service
public class PublishService

{
	@Autowired
	JmsTemplate jmsTemplate;

	@Autowired
	Article article;

	@Autowired
	SchemaValidator schemaValidator;

	@Value("${inbound.endpoint}")
	private String inboundEndpoint;

	/**
	 * 
	 * @param articleDto -This is an ArticleDto object.
	 * @return This return statement returns the response in case of a successful
	 *         post request.
	 * @throws InvalidInputException
	 * @throws JsonProcessingException
	 */

	public void publishArticle(ArticleDto articleDto) throws JsonProcessingException {

		schemaValidator.validateArticle(articleDto);

		jmsTemplate.convertAndSend(inboundEndpoint, article.articleBuilder(articleDto));

	}

}
