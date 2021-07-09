package com.learning.search.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learning.search.model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

	List<Product> findByNameContains(String name);

}
