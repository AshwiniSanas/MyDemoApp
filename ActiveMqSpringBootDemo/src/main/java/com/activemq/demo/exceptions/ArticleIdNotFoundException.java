package com.activemq.demo.exceptions;


/**
 * This class displays the message when ArticleIdNotFoundException is thrown.
 * @author Ashwini.Sanas
 *
 */

public class ArticleIdNotFoundException extends RuntimeException 

{
	/**
	 * 
	 * @param articleId
	 * -This variable holds the value of articleId
	 */
	
	public ArticleIdNotFoundException(int articleId) {
        super("article id not found : " + articleId);
    }

}
