package com.activemq.demo.test.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import org.everit.json.schema.ValidationException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jms.core.JmsTemplate;
import com.activemq.demo.SchemaValidator;
import com.activemq.demo.model.Article;
import com.activemq.demo.model.ArticleDto;
import com.activemq.demo.service.PublishService;

@SpringBootTest
class PublishServiceTest {

	private ArticleDto articleDto;

	private Article article;

	@Mock
	private Article articleMock;

	@Mock
	private JmsTemplate jmsTemplate;

	@Mock
	private SchemaValidator schemaValidator;

	@InjectMocks
	private PublishService publishService = new PublishService();

	private String message;

	@BeforeEach
	void setUp() {

		articleDto = ArticleDto.builder().articleId(11).shortTitle("Physics I").title("hdh").noOfPages(100)
				.authorName("sham").authorEmailAddress("sham@gmail.com").isActive(true).isPublished(true).build();

		ArticleDto articleDTO = ArticleDto.builder().articleId(1).shortTitle("av").title("hfj").noOfPages(10)
				.authorName("jf").authorEmailAddress("vhf").isActive(true).isPublished(false).build();

		article = Article.builder().articleId((Integer) articleDTO.getArticleId())
				.shortTitle((String) articleDTO.getShortTitle()).title((String) articleDTO.getTitle())
				.noOfPages((Integer) articleDTO.getNoOfPages()).authorName((String) articleDTO.getAuthorName())
				.authorEmailAddress((String) articleDTO.getAuthorEmailAddress())
				.isActive((Boolean) articleDTO.getIsActive()).isPublished((Boolean) articleDTO.getIsPublished())
				.build();

		message = null;

	}

	@AfterEach
	void tearDown() {
		// articleDto = null;
	}

	@Test
	void publishArticle_returns_response_status_as_OK() throws Exception {

		doNothing().when(schemaValidator).validateArticle(any());
		doNothing().when(jmsTemplate).convertAndSend(message, article);
		publishService.publishArticle(articleDto);
		verify(schemaValidator, times(1)).validateArticle(any());

	}

	@Test
	void publishArticleThrowsValidationExcepion() throws Exception {
		doThrow(ValidationException.class).when(schemaValidator).validateArticle(any());
		assertThrows(ValidationException.class, () -> publishService.publishArticle(articleDto));
	}
}
