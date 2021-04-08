package com.activemq.demo.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.activemq.demo.exceptions.ArticleIdNotFoundException;
import com.activemq.demo.model.Article;
import com.activemq.demo.repository.ArticleRepository;

@Service
public class ArticleService

{
	@Autowired
	private ArticleRepository articleRepository;

	/**
	 * 
	 * @return This return statement gives a method call to findAll method in
	 *         ArticleRepository class.
	 */

//	@Transactional
//	public Page<Article> findAll() {
//
//		Pageable pageable = PageRequest.of(0, 10);
//		PageRequest.of(1, 10);
//		PageRequest.of(2, 10);
//		PageRequest.of(3, 10);
//		PageRequest.of(4, 10);
//		PageRequest.of(5, 10);
//		Page<Article> page =articleRepository.findAll(pageable);
//		
//		return articleRepository.findAll(pageable);
//	}

	@Transactional
	public List<Article> findAll() {

		return articleRepository.findAll();
	}

	/**
	 * 
	 * @param articleId -This variable holds the value for articleId
	 * @return This return statement gives a method call to findById method in
	 *         ArticleRepository class.
	 * @throws ArticleIdNotFoundException
	 */

	@Transactional
	public Article findById(int articleId) {
		return articleRepository.findById(articleId).orElseThrow(() -> new ArticleIdNotFoundException(articleId));
	}

	/**
	 * 
	 * @param articleId -This variable holds the value for articleId
	 * @return This return statement receives response in a string format.
	 * @throws ArticleIdNotFoundException
	 */

	@Transactional
	public void deleteById(int articleId) {

		articleRepository.deleteById(articleId);

	}

	/**
	 * 
	 * @param article -This is an article object to be saved in the database.
	 * @return This return statement receives response in a string format
	 * @throws Exception
	 */

	@Transactional
	public Article save(Article article) {

		return articleRepository.save(article);
	}
}
