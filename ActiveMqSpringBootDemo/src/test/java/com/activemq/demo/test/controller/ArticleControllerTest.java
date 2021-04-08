package com.activemq.demo.test.controller;

import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.activemq.demo.controller.ArticleController;
import com.activemq.demo.exceptions.ArticleIdNotFoundException;
import com.activemq.demo.model.Article;
import com.activemq.demo.service.ArticleService;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
class ArticleControllerTest {

	@MockBean
	ArticleService articleService;

	@InjectMocks
	ArticleController articleController = new ArticleController();

	@Autowired
	MockMvc mockMvc;

	private Article article1;
	private Article article2;

	@BeforeEach
	public void setUp() {
		article1 = Article.builder().articleId(1).authorEmailAddress("cjn").authorName("njns").isActive(true)
				.isPublished(false).noOfPages(10).title("cnn").shortTitle("jcj").build();

		article2 = Article.builder().articleId(2).authorEmailAddress("mno").authorName("jkl").isActive(true)
				.isPublished(false).noOfPages(101).title("abc").shortTitle("def").build();
	}

	@AfterEach
	public void tearDown() {
		article1 = null;
		article2 = null;
	}

	@Test
	void save_returns_response_status_as_OK() throws Exception {

		ObjectMapper om = new ObjectMapper();

		when(articleService.save(article1)).thenReturn(article1);

		MvcResult result = mockMvc.perform(post("/articles").content(om.writeValueAsString(article1))
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn();

		assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());

		verify(articleService, times(1)).save(any(Article.class));

	}

	@Test
	void save_returns_response_as_INTERNAL_SERVER_ERROR() throws Exception {
		ObjectMapper om = new ObjectMapper();
		doThrow(IllegalArgumentException.class).when(articleService).save(any(Article.class));
		MvcResult result = mockMvc.perform(post("/articles").content(om.writeValueAsString(article1))
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)).andExpect(status().is5xxServerError())
				.andReturn();

		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), result.getResponse().getStatus());

		verify(articleService, times(1)).save(any(Article.class));

	}

	@Test
	void findAll_retuns_list_of_articles() throws Exception {

		List<Article> articles = Arrays.asList(article1, article2);
		when(articleService.findAll()).thenReturn(articles);
		mockMvc.perform(MockMvcRequestBuilders.get("/articles"))
				.andExpect(content().contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$[0].articleId", is(1))).andExpect(jsonPath("$[0].title", is("cnn")))
				.andExpect(jsonPath("$[0].shortTitle", is("jcj"))).andExpect(jsonPath("$[0].noOfPages", is(10)))
				.andExpect(jsonPath("$[0].authorName", is("njns")))
				.andExpect(jsonPath("$[0].authorEmailAddress", is("cjn")))
				.andExpect(jsonPath("$[0].isActive", is(true))).andExpect(jsonPath("$[0].isPublished", is(false)))
				.andExpect(jsonPath("$[1].articleId", is(2))).andExpect(jsonPath("$[1].title", is("abc")))
				.andExpect(jsonPath("$[1].shortTitle", is("def"))).andExpect(jsonPath("$[1].noOfPages", is(101)))
				.andExpect(jsonPath("$[1].authorName", is("jkl")))
				.andExpect(jsonPath("$[1].authorEmailAddress", is("mno")))
				.andExpect(jsonPath("$[1].isActive", is(true))).andExpect(jsonPath("$[1].isPublished", is(false)));
		verify(articleService, times(1)).findAll();

	}

	@Test
	void findById_returns_article() throws Exception {
		when(articleService.findById(1)).thenReturn(article1);
		mockMvc.perform(MockMvcRequestBuilders.get("/articles/1").accept(MediaType.APPLICATION_JSON))
				.andExpect(content().contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$.articleId", is(1))).andExpect(jsonPath("$.title", is("cnn")))
				.andExpect(jsonPath("$.shortTitle", is("jcj"))).andExpect(jsonPath("$.noOfPages", is(10)))
				.andExpect(jsonPath("$.authorName", is("njns"))).andExpect(jsonPath("$.authorEmailAddress", is("cjn")))
				.andExpect(jsonPath("$.isActive", is(true))).andExpect(jsonPath("$.isPublished", is(false)));
		verify(articleService, times(1)).findById(1);

	}

	@Test
	void deleteById_returns_response_OK() throws Exception {
		doNothing().when(articleService).deleteById(anyInt());
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.delete("/articles/1")).andExpect(status().isOk())
				.andReturn();
		assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
		verify(articleService, times(1)).deleteById(1);
	}

	@Test
	void deleteById_returns_response_NOT_FOUND() throws Exception {
		doThrow(ArticleIdNotFoundException.class).when(articleService).deleteById(anyInt());
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.delete("/articles/1"))
				.andExpect(status().isNotFound()).andReturn();
		assertEquals(HttpStatus.NOT_FOUND.value(), result.getResponse().getStatus());
		verify(articleService, times(1)).deleteById(1);
	}
}