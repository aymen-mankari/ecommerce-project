package com.ecommerceproject.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ecommerceproject.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

//	@Query("select p from product p where p.designation like %?1%")
//	List<Product> findByDesignation(String keyword);
	List<Product> findByDesignationIgnoreCaseContaining(String keyword);
}
