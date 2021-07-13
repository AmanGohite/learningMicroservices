package com.learning.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learning.userservice.model.Customers;

public interface UserRepository extends JpaRepository<Customers, Integer> {
	
	Customers findByEmailAndPassword(String emailId, String password);

	Customers findByEmail(String email);

}
