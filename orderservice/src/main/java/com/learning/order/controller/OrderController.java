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

import com.learning.order.model.GenericAPIResponse;
import com.learning.order.model.Orders;
import com.learning.order.service.OrderService;

@RestController
@RequestMapping(value = "order")
public class OrderController {

	@Autowired
	OrderService service;

	@GetMapping(value = "/{custid}")
	public List<Orders> getCustomerOrders(@PathVariable("custid") Integer custId) {

		return service.getCustOrders(custId);

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
	public List<Orders> getOrderInDuration(@PathVariable("custId") Integer custId,
			@PathVariable("month") Integer month) {
		return service.getOrderInDuration(custId, month);
	}
}
