package com.infy.microservices.repository;

import org.springframework.data.repository.CrudRepository;

import com.infy.microservices.entity.Product;

public interface ProductMSRepository extends CrudRepository<Product, Integer> {

	
}
