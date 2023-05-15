package com.coppel.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
public class ApiCoppelApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiCoppelApplication.class, args);
	}
	/*
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/greeting-javaconfig").allowedOrigins("http://localhost:8080");
			}
		};
	}
	

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				//registry.addMapping("/**").allowedOrigins("http://localhost:8080");
				registry.addMapping("/**").allowedOrigins("*");
			}
		};
	}*/
}
