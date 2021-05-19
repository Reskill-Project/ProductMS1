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
import org.springframework.web.server.ResponseStatusException;

import com.infy.microservices.dto.ProductDTO;
import com.infy.microservices.service.ProductService;

@CrossOrigin
@RestController
@RequestMapping("/product")
public class ProductAPI {

	@Autowired
	private ProductService productService;
	
	@Autowired
	private Environment environment;
	
	@GetMapping(value = "/products")
	public ResponseEntity<List<ProductDTO>> allProducts(){
		try {
			List<ProductDTO> products = productService.findAllProducts();
			return new ResponseEntity<List<ProductDTO>>(products, HttpStatus.OK);
		}
		catch(Exception e){
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, environment.getProperty(e.getMessage()));
		}
	}
	
	@GetMapping(value = "/{prodId}/{quantity}")
	public String getProductAvailablility(@PathVariable("prodId") Integer prodId, @PathVariable("quantity") Integer quantity){
		try {
			boolean val = productService.findProduct(prodId, quantity);
			if(val) {
				String s= "Items are available for the given quantity";
				return s;
			}
			else {
				String s= "Quantity is unavailable";
				return s;
			}
		}
		catch(Exception e){
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, environment.getProperty(e.getMessage()));
		}
	}
	
	
	@PostMapping(value = "/add")
	public ResponseEntity<String> addProduct(@Valid @RequestBody ProductDTO productDTO){
		try {
			Integer prodId = productService.addProduct(productDTO);
			String successMessage = environment.getProperty("ProductAPI.add_success")+prodId;
			return new ResponseEntity<String>(successMessage,HttpStatus.OK);
		}
		catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, environment.getProperty(e.getMessage()));
		}
	}
	
	@GetMapping(value = "/product/{prodId}")
	public ProductDTO getProduct(@PathVariable Integer prodId){
			ProductDTO product = productService.getProduct(prodId);
			return product;
	}
	
}
