package com.olx.user.service;

import java.util.Collections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class UserServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserServiceApplication.class, args);
	}
	
	@Bean
    public Docket swaggerConiguration() {
	return new Docket(DocumentationType.SWAGGER_2).select()
												  .apis(RequestHandlerSelectors.any())
												  .paths(PathSelectors.any())
												  .build()
												  .apiInfo(new ApiInfo(
															"User Microservice API",
															"API for User Microservice",
															"1.0",
															"Free to use",
															new springfox.documentation.service.Contact("Sakib Ademovic","www.example.com","sademovic2@etf.unsa.ba"),
															"API License",
															"www.example.com",
															Collections.emptyList()));
	}

}
