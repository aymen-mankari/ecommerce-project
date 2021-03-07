package com.ecommerceproject.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerceproject.entities.Customer;

public interface CustomerRepository extends JpaRepository<Long, Customer> {

}
