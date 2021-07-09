package com.learning.order.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.learning.order.model.Orders;

public interface OrderRepository extends JpaRepository<Orders, Integer> {

	List<Orders> findByCustId(Integer custId);


	@Query("select order from Orders order where order.custId=?1 and datediff(curdate(),order.dateOrdered)>= ?2")
	List<Orders> findByDateBefore(Integer custId,Integer month);
	

}
