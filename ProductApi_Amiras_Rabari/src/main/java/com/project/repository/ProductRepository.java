package com.project.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

	public Optional<Product> findByName(String name);

	public Optional<Product> findById(long id);

	public boolean existsByName(String name);

	public boolean existsById(long id);
}
