package com.learning.search.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learning.search.model.Product;
import com.learning.search.service.ProductService;

import io.swagger.models.Response;

@RestController
@RequestMapping(value="/product")
public class ProductController {
	
	@Autowired
	ProductService productService;
	
	@PostMapping(value="/")
	public ResponseEntity<Void> addProduct(@RequestBody Product product){
		boolean flag = productService.addProduct( product);
		if(flag) {
		return new ResponseEntity<Void>(HttpStatus.CREATED);
		}else {
		return new ResponseEntity<Void>(HttpStatus.CONFLICT);	
		}
	}
	
	@GetMapping(value="/{name}")
	public List<Product> getProductByName(@PathVariable("name") String name){
		return productService.getProductByName(name);
	}
	
	@GetMapping(value="/info/{id}")
	public Product getProductById(@PathVariable("id") Integer id){
		return productService.getProductById(id);
	}
}
