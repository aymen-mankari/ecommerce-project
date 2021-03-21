package com.ecommerceproject.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerceproject.dao.OrderLineRepository;
import com.ecommerceproject.entities.OrderLine;

@Service(value = "OrderLineImpl")
public class OrderLineImpl implements IOrderLineService {
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
	public OrderLine save(OrderLine orderLine) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OrderLine update(OrderLine orderLine) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub

	}

}
