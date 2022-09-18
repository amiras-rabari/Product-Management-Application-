package com.project.service;

import java.util.List;

import com.project.model.Product;



public interface ProductRestClient {

	List<Product> getProducts();
	Product getProductById(long id);
	Product getProductByname(String name);
	Product createProduct(Product product);
	Product updateProduct(long id,Product product);
	void deleteUser(long id);
}
