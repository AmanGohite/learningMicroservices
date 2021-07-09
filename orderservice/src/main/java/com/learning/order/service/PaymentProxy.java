 package com.learning.order.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.learning.order.model.FundTransfer;
import com.learning.order.model.ReversePayment;
import com.learning.order.model.TransferRequest;

@FeignClient(value = "payment-service",url="http://localhost:8005")
 public interface PaymentProxy {

     @RequestMapping(method = RequestMethod.POST, value = "/customer/transfer")
      FundTransfer doPayment(@RequestBody TransferRequest payment);

     @RequestMapping(method=RequestMethod.POST, value = "/customer/reverse")
	void reversePayment(@RequestBody ReversePayment reversepay);
   
}