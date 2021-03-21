package com.ecommerceproject.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerceproject.entities.OrderLine;

public interface OrderLineRepository extends JpaRepository<OrderLine, Long> {

}
