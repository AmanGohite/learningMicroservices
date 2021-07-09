package com.learning.userservice.model;

public class PlaceOrder {

	private Integer productId;
	private Integer quantity;
	private Integer customerId;
	
	public PlaceOrder() {}
	
	
	
	public PlaceOrder(Integer productId, Integer quantity, Integer customerId) {
		super();
		this.productId = productId;
		this.quantity = quantity;
		this.customerId = customerId;
	}



	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public Integer getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
	
	
}
