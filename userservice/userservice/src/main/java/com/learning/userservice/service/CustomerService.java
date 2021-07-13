package com.learning.userservice.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.learning.userservice.model.Orders;
import com.learning.userservice.model.PlaceOrder;
import com.learning.userservice.model.Product;
import com.learning.userservice.model.Cart;
import com.learning.userservice.model.Customer;
import com.learning.userservice.model.CustomerLogin;
import com.learning.userservice.model.Customers;
import com.learning.userservice.model.GenericAPIResponse;
import com.learning.userservice.repository.UserRepository;
import com.mysql.cj.x.protobuf.MysqlxCrud.Order;

import ch.qos.logback.classic.Logger;

@Service
public class CustomerService {
	@Autowired
	OrderProxy orderProxy;
	
	@Autowired
	ProductProxy productProxy;

	@Autowired
	UserRepository userRepo;

	private final Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());

	public GenericAPIResponse addCustomer(Customers customer) {
		// TODO Auto-generated method stub
		GenericAPIResponse resp = new GenericAPIResponse();
		try {
			Customers c = userRepo.findByEmail(customer.getEmail());
			if(c != null) {
				resp.setType("error");
				resp.setMessage("email id already exist");
				return resp;
			}
			
			userRepo.save(customer);

			resp.setType("info");
			resp.setMessage("successfully registered to application please proceed to login !!");
			return resp;
		} catch (Exception e) {
			logger.error("error adding customer {}", e.getMessage());
		}
		resp.setType("error");
		resp.setMessage("error registering user to application");
		return resp;
	}

	public Customer loginCustomer(CustomerLogin login) {
		try {
			Customers customer = userRepo.findByEmailAndPassword(login.getEmail(), login.getPassword());
			if (customer != null) {
				Customer cust = new Customer(customer.getCustomerId(), customer.getName(), customer.getEmail());
				cust.setOrderList(this.addOrdersToResponse(customer.getCustomerId()));
				return cust;
			}
		} catch (Exception e) {
			logger.error("error loggin customer {}", e.getMessage());
		}

		return null;

	}

	private List<Orders> addOrdersToResponse(Integer custId){
		List<Orders> orderList = new ArrayList();
		try {
			orderList =orderProxy.getOrders(custId);
		}catch (Exception e) {
			logger.error("error getting orders from order service {}",e.getMessage());
		}
		return orderList;
		}
	
	public List<Orders> getCustomerOrders(Integer custId) {
		List<Orders> orders = new ArrayList();
		orders.addAll(orderProxy.getOrders(custId));
		return orders;
	}

	public List<Orders> getPastOrdersByMonth(Integer custId,Integer month) {
		List<Orders> orders = new ArrayList();
		orders.addAll(orderProxy.getPastOrders(custId,month));
		return orders;
	}

	public GenericAPIResponse placeOrder(PlaceOrder placeOrder) {
		GenericAPIResponse resp = new GenericAPIResponse();
		try {
			List<Cart> cartProducts = orderProxy.getCart(placeOrder.getCustomerId(),placeOrder.getCartId());
			List<Product> prodDetails = new ArrayList<>();
			for (Cart c : cartProducts){
			prodDetails.add(productProxy.getProduct(c.getProductList()));
			
			}
			
			Double price = prodDetails.stream().filter(o -> o.getPrice() > 0).mapToDouble(o -> o.getPrice()).sum();
			Integer quantity = 1;

			if(cartProducts != null) {
				Orders order = new Orders(placeOrder.getCustomerId(), "", price, quantity,placeOrder.getCartId());
				resp = orderProxy.placeOrder(order);
			}else {
				resp.setType("error");
				resp.setMessage("error getting product details to order");
			}
			return resp;
		}catch (Exception e) {
				logger.error("error placing order for user {}",e.getMessage());
			}
			resp.setType("error");
			resp.setMessage("error placing order");
			return resp;
		
	}

	private Product getProductDetails(Integer productId) {
		try {
			Product p = productProxy.getProduct(productId);
			return p;
		}catch (Exception e) {
			logger.error("error getting product details {}",e.getMessage());
		}
		return null;
	}
}
