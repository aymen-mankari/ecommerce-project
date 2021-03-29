package com.ecommerceproject.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.ecommerceproject.entities.Order;
import com.ecommerceproject.entities.OrderLine;

public interface IOrderService {
	Optional<Order> get(Long id);

	List<Order> getAll();

	Order save(Order order);

	Order update(Order order);

	void delete(Long id);

	List<Order> getOrdersByCustomer(Long id);

	List<Order> findOrdersBetweenTwoDates(LocalDate startDate, LocalDate endDate);
}
