 package com.learning.userservice.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.learning.userservice.model.Cart;
import com.learning.userservice.model.GenericAPIResponse;
import com.learning.userservice.model.Orders;

@FeignClient(name = "order-service",url="http://localhost:8002")

 public interface OrderProxy {

     @RequestMapping(method = RequestMethod.GET, value = "/order/{custId}")
     List<Orders> getOrders(@PathVariable("custId") Integer custId);
     
     @RequestMapping(method = RequestMethod.GET, value = "/order/{custId}/duration/{month}")
     List<Orders> getPastOrders(@PathVariable("custId") Integer custId,@PathVariable("month") Integer month);

     @RequestMapping(method = RequestMethod.POST, value = "/order/")
     GenericAPIResponse placeOrder(@RequestBody Orders order);
     
     @RequestMapping(method = RequestMethod.GET, value = "/order/cart/{custId}/{cartId}")
     List<Cart> getCart(@PathVariable("custId") Integer customerId, @PathVariable("cartId") Integer cartId);
}