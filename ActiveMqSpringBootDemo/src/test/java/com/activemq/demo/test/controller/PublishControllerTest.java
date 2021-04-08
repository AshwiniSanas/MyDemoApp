package com.activemq.demo.test.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.everit.json.schema.ValidationException;
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
import com.activemq.demo.controller.PublishController;
import com.activemq.demo.model.ArticleDto;
import com.activemq.demo.service.PublishService;
import com.fasterxml.jackson.databind.ObjectMapper;


@SpringBootTest
@AutoConfigureMockMvc
class PublishControllerTest {

    @MockBean
    PublishService publishService;

    @InjectMocks
    PublishController publishController = new PublishController();

    private ArticleDto articleDto;

    @Autowired
    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        articleDto = ArticleDto.builder().articleId(1)
                .authorEmailAddress("cjn").authorName("njns").isActive(true).isPublished(false).noOfPages(10)
                .title("cnn").shortTitle("jcj").build();
    }

    @AfterEach
    void tearDown() {
    	articleDto =null;
    }

    @Test
    void publishArticle_returns_response_OK() throws Exception {
        ObjectMapper om = new ObjectMapper();
        doNothing().when(publishService).publishArticle(articleDto);
        MvcResult result = mockMvc.perform(post("/publisher/articles")
                .content(om.writeValueAsString(articleDto))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();
        assertEquals(HttpStatus.OK.value(),result.getResponse().getStatus());
        verify(publishService,times(1)).publishArticle(any(ArticleDto.class));
    }

    @Test
    void publishArticle_returns_response_BAD_REQUEST() throws Exception {

        ObjectMapper om = new ObjectMapper();
        doThrow(new ValidationException(null,String.class,Integer.class,null)).when(publishService).publishArticle(any());
        MvcResult result = mockMvc.perform(post("/publisher/articles")
                .content(om.writeValueAsString(articleDto))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest()).andReturn();
        assertEquals(HttpStatus.BAD_REQUEST.value(),result.getResponse().getStatus());
        verify(publishService,times(1)).publishArticle(any(ArticleDto.class));
    }
}