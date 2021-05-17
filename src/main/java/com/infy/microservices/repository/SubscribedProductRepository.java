package com.infy.microservices.repository;

import org.springframework.data.repository.CrudRepository;

import com.infy.microservices.entity.CompositeKey;
import com.infy.microservices.entity.SubscribedProduct;

public interface SubscribedProductRepository extends CrudRepository<SubscribedProduct, CompositeKey> {
	Iterable <SubscribedProduct> findByIdBuyerId(Integer buyerId);
}
