package com.infy.microservices.service;

import java.util.List;

import com.infy.microservices.dto.SubscribedProductDTO;
import com.infy.microservices.exception.ProductMSException;

public interface SubscribedProductService {
	public List<SubscribedProductDTO> findAllSubscribedProducts(Integer buyerId) throws ProductMSException;
	public Integer addSubscription(SubscribedProductDTO subscribedProductDTO);
}
