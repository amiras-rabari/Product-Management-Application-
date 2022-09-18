package com.project.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.project.model.Product;
import com.project.service.NameExistException;
import com.project.service.ProductService;

@Controller

public class WebController {

	private ProductService service;

	@Autowired
	public WebController(ProductService service) {
		super();
		this.service = service;
	}
	
	@GetMapping
	public String goToIndex() {
		return "index";
	}
	
	@GetMapping("/allProduct")
	public String getAllProduct(Model model) {
		List<Product> products = service.getAllProducts();
		
		model.addAttribute("productsList",products);
		
		return "Products";
	}
	
	
	@GetMapping("/register")
	public String goToRegistration(Model model) {
		model.addAttribute("product", new Product());
		return "registerProduct";
	}
	
	@PostMapping("/createProduct")
	public String createProduct(Product product, Model model)  {
		Product savedProduct = service.createProduct(product);
		model.addAttribute("savedProduct",savedProduct);
		return "confirmation";
	}
	
	@GetMapping("/{name}")
	public String getProduct(@PathVariable String name,Model model) {
		
		Product product = service.getProductByName(name);
		model.addAttribute("retrievedProduct",product);
		return "Product_detail" ;
		}
	
	@GetMapping("/update/{id}")
	public String update(@PathVariable int id,Model model) {
		Product product = service.getProductById(id);
		model.addAttribute("product",product);
		return "update";
	}
	@PostMapping("/updateProduct/{id}")
	public String updateProduct(@PathVariable int id,Product product) {
		Product product1 = service.getProductById(id);
		Product updatedProduct = service.updateProduct(product1.getId(), product);
		return "redirect:/"+updatedProduct.getName();
	}

	@GetMapping("/delete/{id}")
	public String delete(@PathVariable int id,Product product) {
//		Product product1 = service.getProductById(id);
		service.deleteProduct(id);
		return "redirect:/allProduct";
	}
}
	

