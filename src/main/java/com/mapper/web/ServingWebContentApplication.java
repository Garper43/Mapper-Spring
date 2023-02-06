package com.mapper.web;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
public class ServingWebContentApplication {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(ServingWebContentApplication.class);

		app.run(args);
	}
}
