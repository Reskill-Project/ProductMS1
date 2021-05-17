package com.infy.microservices.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infy.microservices.dto.ProductDTO;
import com.infy.microservices.entity.Product;
import com.infy.microservices.exception.ProductMSException;
import com.infy.microservices.repository.ProductMSRepository;

@Service(value="productService")
@Transactional
public class ProductServiceImpl implements ProductService{
	
	@Autowired
	private ProductMSRepository productMSRepository;

	@Override
	public List<ProductDTO> findAllProducts() throws ProductMSException {
		Iterable <Product> products = productMSRepository.findAll();
		List<ProductDTO> productDTOs = new ArrayList<>();
		products.forEach(product->{
			ProductDTO prod = new ProductDTO();
			prod.setProdId(product.getProdId());
			prod.setName(product.getName());
			prod.setPrice(product.getPrice());
			prod.setStock(product.getStock());
			prod.setDescription(product.getDescription());
			prod.setImage(product.getImage());
			prod.setSellerId(product.getSellerId());
			prod.setCategory(product.getCategory());
			prod.setSubcategory(product.getSubcategory());
			prod.setRating(product.getRating());
			productDTOs.add(prod);
		});
		if(productDTOs.isEmpty()) {
			throw new ProductMSException("ProductService.NO_PRODUCTS_AVAILABLE");
		}
		return productDTOs;
	}

	@Override
	public Integer addProduct(ProductDTO productDTO) throws ProductMSException {
		Product product = new Product();
		product.setCategory(productDTO.getCategory());
		product.setDescription(productDTO.getDescription());
		product.setImage(productDTO.getImage());
		product.setName(productDTO.getName());
		product.setPrice(productDTO.getPrice());
		product.setRating(productDTO.getRating());
		product.setSellerId(productDTO.getSellerId());
		product.setStock(productDTO.getStock());
		product.setSubcategory(productDTO.getSubcategory());
		productMSRepository.save(product);
		return product.getProdId();
	}

}
