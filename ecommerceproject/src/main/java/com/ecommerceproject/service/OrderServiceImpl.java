package com.ecommerceproject.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerceproject.dao.OrderLineRepository;
import com.ecommerceproject.dao.OrderRepository;
import com.ecommerceproject.entities.Order;
import com.ecommerceproject.entities.OrderLine;

@Service(value = "OrderServiceImpl")
public class OrderServiceImpl implements IOrderService {
	@Autowired
	private OrderRepository orderRepository;

	@Override
	public Optional<Order> get(Long id) {
		return orderRepository.findById(id);
	}

	@Override
	public List<Order> getAll() {
		return orderRepository.findAll();
	}

	@Override
	public Order save(Order order) {
		boolean res = order.addOrderLinesAndCalculTotal(order.getOrderLines());
		if (res) {
			return orderRepository.save(order);
		} else {
			return null;
		}
	}

	@Override
	public Order update(Order order) {
		return orderRepository.save(order);
	}

	@Override
	public void delete(Long id) {
		orderRepository.deleteById(id);
	}

	@Override
	public List<Order> getOrdersByCustomer(Long id) {
		return orderRepository.findByOrder_Customer_Id(id);
	}

	@Override
	public List<Order> findOrdersBetweenTwoDates(LocalDate startDate, LocalDate endDate) {
		return orderRepository.findOrdersBetweenTwoDates(startDate, endDate);
	}

}
