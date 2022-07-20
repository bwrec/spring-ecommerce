package com.company.Ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.company.Ecommerce.model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
	

}
