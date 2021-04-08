package com.activemq.demo.test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.activemq.demo.SchemaValidator;
import com.activemq.demo.model.ArticleDto;

class SchemaValidatorTest {

	private ArticleDto articleDto;

	@BeforeEach
	void setUp() {
		articleDto = ArticleDto.builder().articleId(1).authorEmailAddress("cjn").authorName("njns").isActive(true)
				.isPublished(false).noOfPages(10).title("cnn").shortTitle("jcj").build();
	}

	@AfterEach
	void tearDown() {
		articleDto = null;
	}

	SchemaValidator schemaValidator = new SchemaValidator();

	@Test
	void validateArticleAgainstSchema_throws_no_exception() {

		assertDoesNotThrow(() -> schemaValidator.validateArticle(articleDto));
	}

}