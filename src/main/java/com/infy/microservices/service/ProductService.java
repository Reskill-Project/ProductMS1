package com.infy.microservices.service;

import java.util.List;

import com.infy.microservices.dto.ProductDTO;
import com.infy.microservices.exception.ProductMSException;

public interface ProductService {
	public List<ProductDTO> findAllProducts() throws ProductMSException;
	public boolean findProduct(Integer prodId, Integer quantity) throws ProductMSException;
	public Integer addProduct(ProductDTO productDTO) throws ProductMSException;
	public void updateProductStock(Integer prodId, Integer quantity) throws ProductMSException;
	public ProductDTO getProduct(Integer productId);
}
