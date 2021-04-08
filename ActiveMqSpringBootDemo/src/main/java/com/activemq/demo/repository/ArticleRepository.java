package com.activemq.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.activemq.demo.model.Article;

/**
 * This is a repository class extending JpaRepository Interface.
 * 
 * @author Ashwini.Sanas
 *
 */

@Repository
public interface ArticleRepository extends JpaRepository<Article, Integer> {

}
