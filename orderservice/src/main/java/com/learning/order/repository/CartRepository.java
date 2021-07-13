package com.learning.order.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learning.order.model.Cart;

public interface CartRepository extends JpaRepository<Cart, Integer>{
	
	Cart findFirstByCustIdOrderByCartId(Integer customerId);

	List<Cart> findByCustIdAndCartId(Integer custId, Integer cartId);

	List<Cart> findByCartId(Integer cartId);


}
