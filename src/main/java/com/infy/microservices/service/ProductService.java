package com.infy.microservices.service;

import java.util.List;

import com.infy.microservices.dto.ProductDTO;
import com.infy.microservices.exception.ProductMSException;

public interface ProductService {
	public List<ProductDTO> findAllProducts() throws ProductMSException;
	public Integer addProduct(ProductDTO productDTO) throws ProductMSException;
}
