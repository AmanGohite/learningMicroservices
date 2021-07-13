package com.learning.order.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name="cart")
public class Cart {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	
	@Column(name="cart_id")
	private Integer cartId;
	
	@Column(name="customer_id")
	private Integer custId;
	
	@Column(name="product_id")
	private Integer productList;
	
	@CreationTimestamp
	private LocalDate date;
	private String status;
	
	public Cart() {}
	
	public Cart(Integer cartId, Integer custId, Integer productList, String status) {
		super();
		this.cartId = cartId;
		this.custId = custId;
		this.productList = productList;
		this.status = status;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCartId() {
		return cartId;
	}

	public void setCartId(Integer cartId) {
		this.cartId = cartId;
	}

	public Integer getCustId() {
		return custId;
	}

	public void setCustId(Integer custId) {
		this.custId = custId;
	}

	public Integer getProductList() {
		return productList;
	}

	public void setProductList(Integer productList) {
		this.productList = productList;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
