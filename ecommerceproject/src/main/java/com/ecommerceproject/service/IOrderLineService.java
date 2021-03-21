package com.ecommerceproject.service;

import java.util.List;
import java.util.Optional;

import com.ecommerceproject.entities.OrderLine;

public interface IOrderLineService {
	Optional<OrderLine> get(Long id);
    List<OrderLine> getAll();
    OrderLine save(OrderLine orderLine);
    OrderLine update(OrderLine orderLine);
    void delete(Long id);
}
