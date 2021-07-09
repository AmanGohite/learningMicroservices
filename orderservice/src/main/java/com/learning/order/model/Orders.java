package com.learning.order.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;


@Entity
@Table(name="orders")
public class Orders {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="order_id")
	private Integer orderId;
	
	@Column(name="cust_id")
	private Integer custId;
	
	@Column(name="order_name")
	private String orderName;
	
	private Double price;
	
	@Column(name="date_ordered")
	@CreationTimestamp
	private LocalDate dateOrdered;
	
	private Integer quantity;
	
	
	public Orders() {}

	
	


	public Orders(Integer orderId, Integer custId, String orderName, Double price, Integer quantity) {
		super();
		this.orderId = orderId;
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





	public Integer getOrderId() {
		return orderId;
	}



	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
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



	public Double getPrice() {
		return price;
	}



	public void setPrice(Double price) {
		this.price = price;
	}





	public LocalDate getDateOrdered() {
		return dateOrdered;
	}





	public void setDateOrdered(LocalDate dateOrdered) {
		this.dateOrdered = dateOrdered;
	}
	
	

}
