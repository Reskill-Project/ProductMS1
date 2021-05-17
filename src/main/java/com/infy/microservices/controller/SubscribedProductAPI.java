package com.infy.microservices.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.infy.microservices.dto.SubscribedProductDTO;
import com.infy.microservices.service.SubscribedProductService;

@CrossOrigin
@RestController
@RequestMapping("/subscribedproduct")
public class SubscribedProductAPI {
	@Autowired
	private SubscribedProductService subscribedProductService;
	
	@Autowired
	private Environment environment;
	
	@GetMapping(value = "/subscriptions/{buyerId}")
	public ResponseEntity<List<SubscribedProductDTO>> getSubscriptions(@PathVariable Integer buyerId){
		try {
			List<SubscribedProductDTO> subs = subscribedProductService.findAllSubscribedProducts(buyerId);
			return new ResponseEntity<List<SubscribedProductDTO>>(subs, HttpStatus.OK);
		}
		catch (Exception e){
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, environment.getProperty(e.getMessage()));
		}
	}
}
