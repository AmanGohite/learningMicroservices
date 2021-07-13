package com.learning.search.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import com.learning.search.model.Product;
import com.learning.search.repository.ProductRepository;

import ch.qos.logback.classic.Logger;

@Service
public class ProductService {

	@Autowired
	ProductRepository prodRepo;
	
	private final Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());

	
	public boolean addProduct(Product product) {
		boolean flag= false;
		try {
			prodRepo.save(product);
			flag=true;
			return flag;
		}catch (Exception e) {
			logger.error("error adding product {}",e.getMessage());
		}
		return flag;
	}
	
	public List<Product> getProductByName(String name){
		List<Product> prodList = new ArrayList();
		try {
			prodList.addAll(prodRepo.findByNameContains(name));
			return prodList;
		}catch (Exception e) {
			logger.error("error searching product {}",e.getMessage());
		}
		return prodList;
	}

	public Product getProductById(Integer id) {
		try {
			Optional<Product> p =prodRepo.findById(id);
			if(p.isPresent())
				return p.get();
		}catch (Exception e) {
			logger.error("error getting products {}",e.getMessage());
			}
		return null;
	}
	
}
