package com.ecommerceproject.dao;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ecommerceproject.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
	List<Order>findByOrder_Customer_Id(Long id);
	
	@Query("select o from ordertable o where o.createdAt between ?1 and ?2 order by o.createdAt desc")
	List<Order> findOrdersBetweenTwoDates(LocalDate startDate, LocalDate endDate);
}
