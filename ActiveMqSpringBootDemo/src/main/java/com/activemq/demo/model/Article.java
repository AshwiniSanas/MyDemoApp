package com.activemq.demo.model;

import lombok.*;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.springframework.stereotype.Component;
import java.io.Serializable;

/**
 * This is an entity class which declares the attributes for the Article
 * @author Ashwini.Sanas
 *This return statement gives a method call to findAll method in ArticleRepository class.
 */

@Component
@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name= "`Article`")
public class Article implements Serializable {
    @Id
    @Column(name = "article_id")
    private int articleId;
    
    @Column(name = "short_title")
    private String shortTitle;
    
    @Column(name = "title")
    private String title;

    @Column(name = "no_of_pages")
    private int noOfPages;

    @Column(name = "author_name")
    private String authorName;

    @Column(name = "author_email_address")
    private String authorEmailAddress;

    @Column(name = "is_active" ,nullable = false)
    private Boolean isActive;

    @Column(name = "is_published", nullable = false)
    private Boolean isPublished;

    public Article articleBuilder(ArticleDto articleDto) {
    	
//    	Article theArticle=new Article();
//    	theArticle.setArticleId(article.getArticleId());
//    	theArticle.setShortTitle(article.getShortTitle());
//    	theArticle.setTitle(article.getTitle());
//    	theArticle.setNoOfPages(article.getNoOfPages());
//    	theArticle.setAuthorName(article.getAuthorName());
//    	theArticle.setAuthorEmailAddress(article.getAuthorEmailAddress());
//    	theArticle.setActive(article.isActive());
//    	theArticle.setPublished(article.isPublished());
//    	return articleService.save(theArticle);

     return Article.builder()
    .articleId((Integer) articleDto.getArticleId())
    .authorEmailAddress((String) articleDto.getAuthorEmailAddress())
    .isActive((Boolean) articleDto.getIsActive())
    .authorName((String) articleDto.getAuthorName())
    .isPublished((Boolean) articleDto.getIsPublished())
    .noOfPages((Integer) articleDto.getNoOfPages())
    .shortTitle((String) articleDto.getShortTitle())
    .title((String) articleDto.getTitle()).build();

}
   
}

