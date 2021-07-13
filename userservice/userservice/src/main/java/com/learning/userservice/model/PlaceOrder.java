package com.learning.userservice.model;

public class PlaceOrder {

	private Integer cartId;
	private Integer customerId;
	
	public PlaceOrder() {}
	
	
	
	public PlaceOrder(Integer cartId, Integer quantity, Integer customerId) {
		super();
		this.cartId = cartId;
		this.customerId = customerId;
	}



	public Integer getCartId() {
		return cartId;
	}



	public void setCartId(Integer cartId) {
		this.cartId = cartId;
	}



	public Integer getCustomerId() {
		return customerId;
	}



	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}


	
}
