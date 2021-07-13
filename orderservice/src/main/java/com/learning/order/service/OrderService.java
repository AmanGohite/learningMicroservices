package com.learning.order.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.learning.order.model.Cart;
import com.learning.order.model.CartDTO;
import com.learning.order.model.FundTransfer;
import com.learning.order.model.Orders;
import com.learning.order.model.ReversePayment;
import com.learning.order.model.TransferRequest;
import com.learning.order.repository.CartRepository;
import com.learning.order.repository.OrderRepository;

import ch.qos.logback.classic.Logger;

@Service
public class OrderService {

	@Autowired
	OrderRepository orderRepo;

	@Autowired
	PaymentProxy paymentProxy;
	
	@Autowired
	CartRepository cartRepo;

	private final Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());

	public List<Orders> getCustOrders(Integer custId) {
		List<Orders> custOrderList = new ArrayList();
		try {
			custOrderList.addAll(orderRepo.findByCustId(custId));
			return custOrderList;
		} catch (Exception e) {
			logger.error("error retreiving customer orders {}", e.getMessage());
		}
		return custOrderList;
	}

	public boolean addCustomerOrder(Orders order) {
		boolean flag = false;
		boolean payment = false;
		try {
			TransferRequest fundDeduction = new TransferRequest(order.getCustId(), 45, "amazon", order.getPrice());
			 payment = this.placeOrder(fundDeduction);
			if (payment) {
				orderRepo.save(order);
				flag = true;
				List<Cart> c =cartRepo.findByCartId(order.getCartId());
				for(Cart cart : c) {
					cart.setStatus("ordered");
					cartRepo.save(cart);
				}
				return flag;
			} else {
				return false;
			}
		} catch (Exception e) {
			if(payment) {
				logger.error("error adding order reversing payment{}", e.getStackTrace());
				ReversePayment reverse = new ReversePayment(order.getCustId(), (order.getPrice()*order.getQuantity()));
				this.reversePayment(reverse);
			}
		}
		return flag;
	}

	public List<Orders> getOrderInDuration(Integer custId, Integer month) {
		List<Orders> custOrderList = new ArrayList();
		try {
			custOrderList.addAll(orderRepo.findByDateBefore(custId, month));
			return custOrderList;
		} catch (Exception e) {
			logger.error("error retreiving customer orders {}", e.getMessage());
		}
		return custOrderList;
	}

	private boolean placeOrder(TransferRequest fundDeduction) {
		try {
			paymentProxy.doPayment(fundDeduction);
			return true;
		} catch (Exception e) {
			logger.error("error processing payment {}", e.getMessage());
		}
		return false;

	}

	private void reversePayment(ReversePayment reverse) {
		try {
			paymentProxy.reversePayment(reverse);

		} catch (Exception e) {
			logger.error("error reversing payment {}", e.getMessage());
		}

	}

	public void addToCart(CartDTO cartdto) {
		try {
			Cart max = cartRepo.findFirstByCustIdOrderByCartId(cartdto.getCustId());
			cartdto.getProductList().forEach(i -> cartRepo
					.save(new Cart((max.getCartId()+1), cartdto.getCustId(), i, "order pending")));
		} catch (Exception e) {
			logger.error("error adding to cart");
		}

	}

	public List<Cart> getCustomerCart(Integer custId, Integer cartId) {
		return cartRepo.findByCustIdAndCartId(custId,cartId);
	}
}
