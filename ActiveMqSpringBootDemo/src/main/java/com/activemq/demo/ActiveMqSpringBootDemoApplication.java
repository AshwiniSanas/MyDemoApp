 package com.activemq.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.activemq.demo.model.Article;
import com.activemq.demo.repository.ArticleRepository;

@SpringBootApplication
public class ActiveMqSpringBootDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ActiveMqSpringBootDemoApplication.class, args);
	}

	
//	@Bean
//	public CommandLineRunner demo(ArticleRepository articleRepository)
//	{
//		return(args->
//		{
//			articleRepository.save(new Article(1,"ash","aja",10,"hs","yjs",true,true));
//			articleRepository.save(new Article(2,"ash","aja",10,"hs","yjs",true,true));
//			articleRepository.save(new Article(3,"ash","aja",10,"hs","yjs",true,true));
//		});
//	}
	
}
