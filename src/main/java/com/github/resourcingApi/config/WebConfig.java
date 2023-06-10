package com.github.resourcingApi.config;

import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class WebConfig implements WebMvcConfigurer {
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		// create an array of allowed addresses
		String[] allowedAddresses = { "http://localhost:5173/", "http://localhost:4173/", "http://127.0.0.1:5173/",
				"http://127.0.0.1:4173/", "http://localhost:3000/" };
		registry.addMapping("/**").allowedOrigins(allowedAddresses).allowedMethods("GET", "POST", "DELETE", "PATCH")
				.allowedHeaders("*");
	}
}
