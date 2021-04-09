package com.ecommerceproject.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerceproject.dao.OrderLineRepository;
import com.ecommerceproject.entities.OrderLine;

@Service(value = "OrderLineServiceImpl")
public class OrderLineServiceImpl implements IOrderLineService {
	@Autowired
	private OrderLineRepository orderLineRepository;

	@Override
	public Optional<OrderLine> get(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<OrderLine> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OrderLine saveOrUpdate(OrderLine orderLine) {
		return orderLineRepository.save(orderLine);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub

	}

}
