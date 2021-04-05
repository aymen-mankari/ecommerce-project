package com.ecommerceproject.dao;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ecommerceproject.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
	@Query("select o from OrderTable o where o.customer.id= :id")
	List<Order>findByOrder_Customer_Id(@Param("id")Long id);
	
	//@Query("select o from ordertable o where o.createdAt between ?1 and ?2")
	@Query(value = "from OrderTable o where o.createdAt BETWEEN :startDate AND :endDate")
	List<Order> findOrdersBetweenTwoDates(@Param("startDate") LocalDate startDate,@Param("endDate") LocalDate endDate);
}
