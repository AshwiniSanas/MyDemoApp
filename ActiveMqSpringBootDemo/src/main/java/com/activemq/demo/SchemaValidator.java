package com.activemq.demo;

import java.io.InputStream;
import org.everit.json.schema.Schema;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.springframework.stereotype.Component;
import com.activemq.demo.model.ArticleDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * This class is used to validate the json object against a json schema
 * 
 * @author Ashwini.Sanas
 *
 */
@Component
public class SchemaValidator {

	/**
	 * 
	 * @param article -This is an ArticleDto object.
	 * @throws JsonProcessingException
	 */
	public void validateArticle(ArticleDto articleDto) throws JsonProcessingException {
		
		InputStream inputStream = getClass().getResourceAsStream("/article.json");

		JSONObject jsonObject = new JSONObject(new JSONTokener(inputStream));

		Schema schema = SchemaLoader.load(jsonObject);

		ObjectMapper om = new ObjectMapper();

		String json = om.writerWithDefaultPrettyPrinter().writeValueAsString(articleDto);

		JSONObject myJson = new JSONObject(json);

		System.out.println(myJson);

		schema.validate(myJson);

	}
}
