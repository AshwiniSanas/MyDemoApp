package com.activemq.demo.test.integrationtest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.activemq.demo.model.Article;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
@ActiveProfiles("test")
 class ControllerIntegrationTest {
	@LocalServerPort
	private int port;

	@Autowired
	private Article article;

//	@Autowired
//	private TestRestTemplate testRestTemplate;

	@Autowired
	MockMvc mockMvc;

	HttpHeaders headers = new HttpHeaders();

	@Test
	void findAllArticlesTest() throws Exception {

		MvcResult mvcResult = mockMvc
				.perform(MockMvcRequestBuilders.get("/articles").accept(MediaType.APPLICATION_JSON)).andReturn();

		assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());

		System.out.println(mvcResult.getResponse().getContentAsString());

	}

	@Test
	void saveArticleTest() throws Exception {
		article = Article.builder().articleId(8).shortTitle("abc").title("efg").noOfPages(11).authorName("jhi")
				.authorEmailAddress("jkl").isActive(true).isPublished(true).build();

		ObjectMapper om = new ObjectMapper();
		MvcResult mvcResult = mockMvc
				.perform(MockMvcRequestBuilders.post("/publisher/articles")
				.content(om.writeValueAsString(article))
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();
		
		assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
		System.out.println(mvcResult.getResponse().getContentAsString());
		
		
	}
	
	@Test
	void articleByIdTest() throws Exception {
		MvcResult mvcResult = mockMvc
				.perform(MockMvcRequestBuilders.get("/articles/8").accept(MediaType.APPLICATION_JSON)).andReturn();

		assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());

		System.out.println(mvcResult.getResponse().getContentAsString());
	}

//	@Test
//	void test_findAll()
//	{
//		List<Article> articles=testRestTemplate.getForObject("http://localhost:" + port + "/articles", List.class);
//		Assertions.assertNotNull(articles);
//		System.out.println(articles);
//		assertEquals(8,articles.size());
//		
//	}

//	@Test
//	void saveArticleTest() {
//
//		article = Article.builder().articleId(4).shortTitle("abc").title("efg").noOfPages(11).authorName("jhi")
//				.authorEmailAddress("jkl").isActive(true).isPublished(true).build();
//
//		HttpEntity<Article> request = new HttpEntity<Article>(article);
//
//		ResponseEntity<Article> response = testRestTemplate.postForEntity("/publisher/articles", request,
//				Article.class);
//
//		assertEquals(HttpStatus.OK.value(), response.getStatusCode().value());
//		// assertEquals(9,response.getBody().getArticleId());
//
//	}


//	@Test
//	void articleByIdTest() {
//		ResponseEntity<Article> response = testRestTemplate.getForEntity("/articles/1", Article.class);
//		assertEquals(1, response.getBody().getArticleId());
//		assertEquals(100, response.getBody().getNoOfPages());
//
//	}

}
