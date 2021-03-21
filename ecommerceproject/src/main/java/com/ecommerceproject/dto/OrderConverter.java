package com.ecommerceproject.dto;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ecommerceproject.entities.Order;

@Component("OrderConverter")
public class OrderConverter implements IConverter<OrderDTO, Order> {

	@Autowired
	private CustomerConverter customerConverter;
	@Autowired
	private OrderLineConverter orderLineConverter;

	@Override
	public OrderDTO convertToDTO(Order o) {
		return new OrderDTO(o.getId(), customerConverter.convertToDTO(o.getCustomer()),
				orderLineConverter.convertToDTOList(o.getOrderLines()), o.getTotal());
	}

	@Override
	public List<OrderDTO> convertToDTOList(List<Order> list) {
		return list.stream().map(this::convertToDTO).collect(Collectors.toList());
	}

	@Override
	public Order convertToBo(OrderDTO o) {
		return new Order(o.getIdOrder(), customerConverter.convertToBo(o.getCustomer()),
				orderLineConverter.convertToBoList(o.getOrderLines()), o.getTotal());
	}

	@Override
	public List<Order> convertToBoList(List<OrderDTO> list) {
		return list.stream().map(this::convertToBo).collect(Collectors.toList());
	}

}
