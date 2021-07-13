package com.learning.userservice.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.learning.userservice.model.Orders;
import com.learning.userservice.model.Product;

@FeignClient(name = "search-service",url="http://localhost:8004")
public interface ProductProxy {
	  @RequestMapping(method = RequestMethod.GET, value = "/product/info/{id}")
	   Product getProduct(@PathVariable("id") Integer id);
}
