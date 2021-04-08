package com.activemq.demo.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.activemq.demo.exceptions.ArticleIdNotFoundException;
import com.activemq.demo.model.Article;
import com.activemq.demo.model.ArticleDto;
import com.activemq.demo.service.ArticleService;
import springfox.documentation.annotations.ApiIgnore;

@RestController
public class ArticleController {
	@Autowired
	ArticleService articleService;

	@Autowired
	Article article;

	/**
	 * @return Returns the method call for findAll method in ArticleService class
	 */

	@GetMapping("/articles")
	public List<Article> findAll() {
		return articleService.findAll();
	}

	/**
	 * @param id -This variable holds the value for articleId
	 * @return Returns the method call for findById method in ArticleService class
	 */

	@GetMapping("/articles/{id}")
	public Article findById(@PathVariable int id) {
		return articleService.findById(id);
	}

	/**
	 * @param article -This is ArticleDto Object which carries the values for
	 *                attributes
	 * @return Returns the method call for save method in ArticleService class
	 */

	@ApiIgnore
	@PostMapping("/articles")
	public ResponseEntity<String> save(@RequestBody ArticleDto articleDto) {
		try {

			articleService.save(article.articleBuilder(articleDto));

			return new ResponseEntity<>("saved", HttpStatus.OK);

		} catch (Exception e) {

			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/articles/{id}")
	public ResponseEntity<String> deleteArticle(@PathVariable int id) throws ArticleIdNotFoundException {

		try {
			articleService.deleteById(id);
			return new ResponseEntity<>("deleted", HttpStatus.OK);
		} catch (Exception e) {

			throw new ArticleIdNotFoundException(id);
		}
	}
}
