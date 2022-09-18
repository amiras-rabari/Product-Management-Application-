package com.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.model.Product;

@Service
public class ProductService {
	
	private ProductWebClient productWebClient;

	@Autowired
	public ProductService(ProductWebClient productWebClient) {
		super();
		this.productWebClient = productWebClient;
	}
	
	public List<Product> getAllProducts(){
		return productWebClient.getProducts();
	}
	
	public Product getProductById(long id) {
		return productWebClient.getProductById(id);
	}
	
	public Product getProductByName(String name) {
		return productWebClient.getProductByname(name);
	}
	
	public Product createProduct(Product product)  {
		return productWebClient.createProduct(product);
	}
	
	public Product updateProduct (long id, Product product) {
		return productWebClient.updateProduct(id, product);
	}
	
	public void deleteProduct(long id) {
		productWebClient.deleteUser(id);
	}
}
