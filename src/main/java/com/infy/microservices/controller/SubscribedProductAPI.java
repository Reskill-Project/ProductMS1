package com.infy.microservices.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
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
	
	@Autowired
	RestTemplate template;
	
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
	
	@PostMapping(value = "/subscribe")
	public ResponseEntity<String> addSubscription(@Valid @RequestBody SubscribedProductDTO subscribedProductDTO){
		try {
			boolean val = template.getForObject("http://USERMS"+"/buyer/"+subscribedProductDTO.getBuyerId(), boolean.class);
			if(val==true) {
				Integer buyerId = subscribedProductService.addSubscription(subscribedProductDTO);
				String message = environment.getProperty("SubscribedProductAPI.success_message")+buyerId;
				return new ResponseEntity<String>(message,HttpStatus.OK);
			}
			else {
				String message = environment.getProperty("SubscribedProductAPI.failure_message");
				return new ResponseEntity<String>(message,HttpStatus.OK);
			}
		}
		catch(Exception e){
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, environment.getProperty(e.getMessage()));
		}
	}
}
