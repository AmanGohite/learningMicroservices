package com.learning.userservice.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.learning.userservice.exception.CustomerNotFoundException;
import com.learning.userservice.model.Customer;
import com.learning.userservice.model.CustomerLogin;
import com.learning.userservice.model.Customers;
import com.learning.userservice.model.GenericAPIResponse;
import com.learning.userservice.model.Orders;
import com.learning.userservice.model.PlaceOrder;
import com.learning.userservice.service.CustomerService;

@RestController
@RequestMapping(value="/customer")
public class CustomerController{
	
	@Autowired
	CustomerService custService;
	
	@PostMapping(value="/login")
	public ResponseEntity<Customer> loginCustomer(@RequestBody @Valid CustomerLogin login){
		Customer resp = custService.loginCustomer(login);
		if (resp == null) {
			throw new CustomerNotFoundException("email id or password is incorrect");
		}
		return new ResponseEntity<Customer>(resp, HttpStatus.OK);

		
	}
	
	@PostMapping(value="/register")
	public ResponseEntity<GenericAPIResponse> addCustomer(@RequestBody @Valid Customers customer){
		GenericAPIResponse resp = custService.addCustomer(customer);
		return new ResponseEntity<GenericAPIResponse>(resp,HttpStatus.CREATED);
	}
	
	@PostMapping(value="/order")
	public ResponseEntity<GenericAPIResponse> placeOrder(@RequestBody PlaceOrder placeOrder){
		GenericAPIResponse resp = custService.placeOrder(placeOrder);
		return new ResponseEntity<GenericAPIResponse>(resp,HttpStatus.OK);
	}
		
	@GetMapping(value="/{custId}")
	public List<Orders> getCustomerOrder(@PathVariable("custId") Integer custId){
		return custService.getCustomerOrders(custId);
	}
	
	@GetMapping(value="/{custId}/order/duration/{month}")
	public List<Orders> getPastOrdersByMonth(@PathVariable("custId")Integer custId,@PathVariable("month") Integer month){
		return custService.getPastOrdersByMonth(custId,month);
	}

}
