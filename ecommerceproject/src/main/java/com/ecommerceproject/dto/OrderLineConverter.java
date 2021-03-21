package com.ecommerceproject.dto;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ecommerceproject.entities.OrderLine;
import com.ecommerceproject.entities.Product;

@Component(value = "OrderLineConverter")
public class OrderLineConverter implements IConverter<OrderLineDTO, OrderLine> {

	@Autowired
	private ProductConverter productConverter;

	@Autowired
	private OrderConverter orderConverter;

	@Override
	public OrderLineDTO convertToDTO(OrderLine o) {
		return new OrderLineDTO(o.getId(), o.getQuantity(), orderConverter.convertToDTO(o.getOrder()),
				productConverter.convertToDTO(o.getProduct()));
	}

	@Override
	public List<OrderLineDTO> convertToDTOList(List<OrderLine> list) {
		return list.stream().map(this::convertToDTO).collect(Collectors.toList());
	}

	@Override
	public OrderLine convertToBo(OrderLineDTO o) {
		return new OrderLine(o.getId(), o.getQuantity(), orderConverter.convertToBo(o.getOrder()),
				productConverter.convertToBo(o.getProduct()));
	}

	@Override
	public List<OrderLine> convertToBoList(List<OrderLineDTO> list) {
		return list.stream().map(this::convertToBo).collect(Collectors.toList());
	}

}
