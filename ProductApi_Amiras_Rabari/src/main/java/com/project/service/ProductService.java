package com.project.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.model.Product;
import com.project.repository.ProductRepository;

@Service
public class ProductService {

	private ProductRepository repo;

	@Autowired
	public ProductService(ProductRepository repo) {
		super();
		this.repo = repo;
	}

	public List<Product> getAllProducts() {
		return repo.findAll();
	}

	public Optional<Product> getProductByName(String name) throws ProductNotFoundException {
		Optional<Product> Product = repo.findByName(name);
		if (!Product.isPresent()) {
			throw new ProductNotFoundException("Product does not exist");
		} else {
			return Product;
		}
	}

	public Optional<Product> getProductById(long id) throws ProductNotFoundException {
		Optional<Product> product = repo.findById(id);

		if (!product.isPresent()) {
			throw new ProductNotFoundException("Product does not exist");
		} else {
			return product;
		}
	}

	public Product createProduct(Product product) throws NameExistException {

		if (repo.existsByName(product.getName())) {
			throw new NameExistException("name already in use");
		} else {
			return repo.save(product);
		}
	}

	public Product updateProduct(long id, Product Product) throws ProductNotFoundException, NameExistException {
		Optional<Product> product = repo.findById(id);

		if (!product.isPresent()) {
			throw new ProductNotFoundException("Product does not exist");
		} else {
			if (repo.existsByName(Product.getName())) {
				throw new NameExistException("name already in use");
			} else {
				return repo.save(Product);
			}
		}

	}

	public void deleteProduct(long id) throws ProductNotFoundException {
		Optional<Product> product = repo.findById(id);

		if (!product.isPresent()) {
			throw new ProductNotFoundException("Product does not exist");
		} else {
			repo.deleteById(id);
		}
	}

}
