package com.activemq.demo.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.google.common.base.Predicate;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import static springfox.documentation.builders.PathSelectors.regex;
import static com.google.common.base.Predicates.or;

/**
 * This class is used to configure Swagger Api.
 * @author Ashwini.Sanas
 *
 */

@Configuration
@EnableSwagger2
public class SwaggerConfig 
{
	
	@Bean
	public Docket postsApi() {
		return new Docket(DocumentationType.SWAGGER_2).groupName("public-api")
				.apiInfo(apiInfo()).select().paths(postPaths()).build();
	}

	/**
	 * 
	 * @return
	 */
	private Predicate<String> postPaths() {
		return or(regex("/articles.*"),regex("/publisher/articles.*"));
	}

	/**
	 * 
	 * @return This return statement is used to provide description for the Swagger Api.
	 */
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("Swagger API")
				.description("ActiveMQ-SpringBoot Article API reference for developers")
				.licenseUrl("javainuse@gmail.com").version("1.0").build();
	}
}
