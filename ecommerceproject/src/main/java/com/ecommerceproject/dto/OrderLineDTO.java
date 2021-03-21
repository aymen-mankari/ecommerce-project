package com.ecommerceproject.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
public class OrderLineDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private int quantity;
	private OrderDTO order;
	private ProductDTO product;
	
	public OrderLineDTO(Long id, int quantity, OrderDTO order, ProductDTO product) {
		super();
		this.id = id;
		this.quantity = quantity;
		this.order = order;
		this.product = product;
	}
	
	
}
