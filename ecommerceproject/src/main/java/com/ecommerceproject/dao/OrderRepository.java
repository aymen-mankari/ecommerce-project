package com.ecommerceproject.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerceproject.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
