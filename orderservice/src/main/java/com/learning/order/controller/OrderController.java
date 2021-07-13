package com.learning.order.controller;

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

import com.learning.order.model.Cart;
import com.learning.order.model.CartDTO;
import com.learning.order.model.GenericAPIResponse;
import com.learning.order.model.Orders;
import com.learning.order.service.OrderService;

@RestController
@RequestMapping(value = "order")
public class OrderController {

	@Autowired
	OrderService service;

	@GetMapping(value = "/{custid}")
	public ResponseEntity<List<Orders>> getCustomerOrders(@PathVariable("custid") Integer custId) {

		return new ResponseEntity<List<Orders>>(service.getCustOrders(custId),HttpStatus.OK);

	}

	@PostMapping(value = "/")
	public ResponseEntity<GenericAPIResponse> addCustomerOrder(@RequestBody Orders order) {
		boolean flag = service.addCustomerOrder(order);
		if (flag) {
			GenericAPIResponse resp = new GenericAPIResponse("info",
					"Payment Successful, Your Order is Placed with us !!");
			return new ResponseEntity<GenericAPIResponse>(resp, HttpStatus.CREATED);
		} else {
			GenericAPIResponse resp = new GenericAPIResponse("error", "Error occured while placing your order");
			return new ResponseEntity<GenericAPIResponse>(resp, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping(value = "/{custId}/duration/{month}")
	public ResponseEntity<List<Orders>> getOrderInDuration(@PathVariable("custId") Integer custId,
			@PathVariable("month") Integer month) {
		return new ResponseEntity<List<Orders>>(service.getOrderInDuration(custId, month),HttpStatus.OK);
	}
	
	@PostMapping(value="/cart")
	public ResponseEntity<Void> addToCart(@RequestBody CartDTO cart){
		service.addToCart(cart);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@GetMapping(value="/cart/{custId}/{cartId}")
	public ResponseEntity<List<Cart>> getCustomerCart(@PathVariable("custId") Integer custId
						,@PathVariable("cartId") Integer cartId){
		return new ResponseEntity<List<Cart>>(service.getCustomerCart(custId,cartId),HttpStatus.OK);
	}
}
