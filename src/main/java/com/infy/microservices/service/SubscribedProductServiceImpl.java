package com.infy.microservices.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infy.microservices.dto.SubscribedProductDTO;
import com.infy.microservices.entity.CompositeKey;
import com.infy.microservices.entity.SubscribedProduct;
import com.infy.microservices.exception.ProductMSException;
import com.infy.microservices.repository.SubscribedProductRepository;

@Service(value="subscribedProductService")
@Transactional
public class SubscribedProductServiceImpl implements SubscribedProductService {

	@Autowired
	private SubscribedProductRepository subscribedRepo;
	@Override
	public List<SubscribedProductDTO> findAllSubscribedProducts(Integer buyerId) throws ProductMSException {
		Iterable<SubscribedProduct> subscribedProducts = subscribedRepo.findByIdBuyerId(buyerId);
		List<SubscribedProductDTO> subscribedProductDTOs = new ArrayList<>();
		subscribedProducts.forEach(product->{
			SubscribedProductDTO sub = new SubscribedProductDTO();
			sub.setBuyerId(buyerId);
			sub.setProdId(product.getId().getProdId());
			sub.setQuantity(product.getQuantity());
			subscribedProductDTOs.add(sub);
		});
		if(subscribedProductDTOs.isEmpty()) {
			throw new ProductMSException("SubscribedProductService.NO_PRODUCTS_AVAILABLE");
		}
		return subscribedProductDTOs;
	}
	@Override
	public Integer addSubscription(SubscribedProductDTO subscribedProductDTO) {
		SubscribedProduct product = new SubscribedProduct();
		CompositeKey newId = new CompositeKey();
		newId.setBuyerId(subscribedProductDTO.getBuyerId());
		newId.setProdId(subscribedProductDTO.getProdId());
		product.setId(newId);
		product.setQuantity(subscribedProductDTO.getQuantity());
		subscribedRepo.save(product);
		return subscribedProductDTO.getBuyerId();
	}
	
}
