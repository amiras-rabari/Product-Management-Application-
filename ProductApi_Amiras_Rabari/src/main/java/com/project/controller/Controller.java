package com.project.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.model.Product;
import com.project.service.NameExistException;
import com.project.service.ProductNotFoundException;
import com.project.service.ProductService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping( "/Product")
public class Controller {

	private ProductService service;

	@Autowired
	public Controller(ProductService service) {
		super();
		this.service = service;
	}

	@Operation(summary = "to get a all products by ")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", content = { @Content(mediaType = MediaType.APPLICATION_JSON_VALUE),
					@Content(mediaType = MediaType.APPLICATION_XML_VALUE) },

					headers = { @Header(name = "location", description = "/Product") }) })

	@GetMapping
	public ResponseEntity<List<Product>> getAllProducts() {
		List<Product> products = service.getAllProducts();
		return ResponseEntity.ok(products);
	}

	@Operation(summary = "to get a Specific product by using name")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", content = { @Content(mediaType = MediaType.APPLICATION_JSON_VALUE),
					@Content(mediaType = MediaType.APPLICATION_XML_VALUE) },

					headers = { @Header(name = "location", description = "/Product/name") }),
			@ApiResponse(responseCode = "404", description = "product does not exist") })

	@GetMapping("name/{name}")
	public ResponseEntity<Optional<Product>> getProductByname(@PathVariable("name") String name)
			throws ProductNotFoundException {
		Optional<Product> product = service.getProductByName(name);
		return ResponseEntity.ok(product);
	}

	@Operation(summary = "to get a Specific product by using Id")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", content = { @Content(mediaType = MediaType.APPLICATION_JSON_VALUE),
					@Content(mediaType = MediaType.APPLICATION_XML_VALUE) },

					headers = { @Header(name = "location", description = "/Product/Id") }),
			@ApiResponse(responseCode = "404", description = "product does not exist") })
	@GetMapping("/{Id}")
	public ResponseEntity<Optional<Product>> getProductById(@PathVariable("Id") long id) throws ProductNotFoundException {
		Optional<Product> product = service.getProductById(id);
		return ResponseEntity.ok(product);
	}

	@Operation(summary = "to create a product ")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", content = { @Content(mediaType = MediaType.APPLICATION_JSON_VALUE),
					@Content(mediaType = MediaType.APPLICATION_XML_VALUE) },

					headers = { @Header(name = "location", description = "/Product") }),
			@ApiResponse(responseCode = "400", description = "Bad request ,validation not passed") })

	@PostMapping
	public ResponseEntity<Product> createProduct(@Valid @RequestBody Product product) throws NameExistException {
		Product savedProduct = service.createProduct(product);
		return ResponseEntity.ok(savedProduct);
	}

	@Operation(summary = "to update a product ")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", content = { @Content(mediaType = MediaType.APPLICATION_JSON_VALUE),
					@Content(mediaType = MediaType.APPLICATION_XML_VALUE) },

					headers = { @Header(name = "location", description = "/Product/Id") }),
			@ApiResponse(responseCode = "400", description = "Bad request ,validation not passed"),
			@ApiResponse(responseCode = "404", description = "product does not exist") })

	@PutMapping("/{id}")
	public ResponseEntity<Product> updateProduct(@PathVariable("id") long id, @Valid @RequestBody Product product)
			throws ProductNotFoundException, NameExistException {
		Product updatedProduct = service.updateProduct(id, product);
		return ResponseEntity.ok(updatedProduct);
	}

	@Operation(summary = "to delete a product ")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", content = { @Content(mediaType = MediaType.APPLICATION_JSON_VALUE),
					@Content(mediaType = MediaType.APPLICATION_XML_VALUE) },

					headers = { @Header(name = "location", description = "/Product/Id") }),
			@ApiResponse(responseCode = "404", description = "product does not exist") })
	
	@DeleteMapping("/{id}")
	public void deleteProduct(@PathVariable("id") long id) throws ProductNotFoundException {

		service.deleteProduct(id);

	}

}
