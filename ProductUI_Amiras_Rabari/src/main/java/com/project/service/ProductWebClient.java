package com.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;


import com.project.model.Product;

import reactor.core.publisher.Mono;

@Component
public class ProductWebClient implements ProductRestClient {

	
	private WebClient productWebClient;
	
	@Autowired
	public ProductWebClient(WebClient productWebClient) {
		super();
		this.productWebClient = productWebClient;
	}

	@Override
	public List<Product> getProducts() {
		
		return productWebClient.get().retrieve().bodyToFlux(Product.class).collectList().block();
	}

	@Override
	public Product getProductById(long id) {
		return productWebClient.get().uri("/"+id).retrieve().onStatus(status -> status.value() == HttpStatus.NOT_FOUND.value(), 
				response ->Mono.error(new ProductNotFoundException("Product does not exist")) ).bodyToMono(Product.class).block();
	}

	@Override
	public Product getProductByname(String name) {
		return productWebClient.get().uri("/name/"+name).retrieve().onStatus(status -> status.value() == HttpStatus.NOT_FOUND.value(), 
				response ->Mono.error(new ProductNotFoundException("Product does not exist")) ).bodyToMono(Product.class).block();
	}

	@Override
	public Product createProduct(Product product) {
		return productWebClient.post().bodyValue(product).retrieve().bodyToMono(Product.class).block();
//		.onStatus(status -> status.value() == HttpStatus.BAD_REQUEST.value(),
//				response ->Mono.error(new NameExistException("Name already exist")))
	}

	@Override
	public Product updateProduct(long id, Product product) {
		return productWebClient.put().uri("/"+id).bodyValue(product).retrieve().onStatus(status -> status.value() == HttpStatus.NOT_FOUND.value(), 
				response ->Mono.error(new ProductNotFoundException("Product does not exist")) ).bodyToMono(Product.class).block();
	}

	@Override
	public void deleteUser(long id) {
		productWebClient.delete().uri("/"+id).retrieve().onStatus(status -> status.value() == HttpStatus.NOT_FOUND.value(), 
				response ->Mono.error(new ProductNotFoundException("Product does not exist")) ).toBodilessEntity().block();


	}

}
