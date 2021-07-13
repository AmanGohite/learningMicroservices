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

public class CartDTO {
	
	
	private Integer custId;
	
	private List<Integer> productList;
	
	public CartDTO() {}

	public CartDTO(Integer custId, List<Integer> productList) {
		super();
		this.custId = custId;
		this.productList = productList;
	}

	public Integer getCustId() {
		return custId;
	}

	public void setCustId(Integer custId) {
		this.custId = custId;
	}

	public List<Integer> getProductList() {
		return productList;
	}

	public void setProductList(List<Integer> productList) {
		this.productList = productList;
	}
	
	
	
	
}
