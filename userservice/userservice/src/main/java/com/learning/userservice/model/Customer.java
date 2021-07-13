package com.learning.userservice.model;

import java.util.List;

public class Customer {

	
	private Integer customerId;
	private String name;
	private String email;
	private List<Orders> orderList;
	
	public Customer() {}
	
	public Customer(Integer customerId, String name, String email) {
		super();
		this.customerId = customerId;
		this.name = name;
		this.email = email;
	}
	public Integer getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public List<Orders> getOrderList() {
		return orderList;
	}
	public void setOrderList(List<Orders> orderList) {
		this.orderList = orderList;
	}
	
	
	
}
