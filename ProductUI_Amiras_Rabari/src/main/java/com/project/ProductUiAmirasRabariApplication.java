package com.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
@EnableEurekaClient
public class ProductUiAmirasRabariApplication {
//
//	private final String BASE_URL ="http://localhost:8081/Product"; 
	private final String BASE_URL ="http://Product-API/Product"; 

	public static void main(String[] args) {
		SpringApplication.run(ProductUiAmirasRabariApplication.class, args);
	}
	
	@Bean
	@LoadBalanced
	public WebClient.Builder getBuilder(){
		return WebClient.builder() ;
	}
	
	@Bean
	public WebClient getWebClient(WebClient.Builder builder) {
		return builder.baseUrl(BASE_URL).defaultHeader(HttpHeaders.CONTENT_TYPE,MediaType.APPLICATION_JSON_VALUE,MediaType.ALL_VALUE).build();
	}

}
