package com.addusername.social;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/*
 * Also I disagree with the idea that services should be converting between dtos and entities.
 *  Once in a while you may find a need for a dto but it shouldn’t be routine. For web applications
 *  that use JSP or thymeleaf you may be able to happily add entities as request attributes and 
 * let the template use them directly. If your controllers need to return JSON, you may be able to 
 * hook up a messageconverter to generate JSON directly from the entity or it may be better to have a dto.
 *  Try to keep the focus on implementing business functionality and avoid moving data from one kind of holder
 *   into a different kind of holder, because that kind of code is brittle and error-prone.
 *   
 *   https://stackoverflow.com/questions/51976807/jpa-correct-way-to-use-service-and-multiple-repositories
 * */
@SpringBootApplication
public class BackApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackApplication.class, args);
	}

}
