package com.learning.userservice.model;

import java.time.LocalDate;

import org.springframework.stereotype.Component;


public class Orders {
	
	private Integer custId;
	private String orderName;
	private Integer orderId;
	private Double price;
	private Integer quantity;
	private LocalDate dateOrdered;
	
	
	public Orders() {}

	
	

	public Orders(Integer custId, String orderName,  Double price, Integer quantity) {
		super();
		this.custId = custId;
		this.orderName = orderName;
		this.price = price;
		this.quantity = quantity;
	}

	
	

	public Integer getQuantity() {
		return quantity;
	}




	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}




	public LocalDate getDateOrdered() {
		return dateOrdered;
	}




	public void setDateOrdered(LocalDate dateOrdered) {
		this.dateOrdered = dateOrdered;
	}




	public Integer getCustId() {
		return custId;
	}



	public void setCustId(Integer custId) {
		this.custId = custId;
	}



	public String getOrderName() {
		return orderName;
	}



	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}





	public Integer getOrderId() {
		return orderId;
	}




	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}




	public Double getPrice() {
		return price;
	}



	public void setPrice(Double price) {
		this.price = price;
	}
	
	
}
