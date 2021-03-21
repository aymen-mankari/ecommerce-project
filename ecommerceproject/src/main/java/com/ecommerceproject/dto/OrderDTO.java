package com.ecommerceproject.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
public class OrderDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long idOrder;
	private CustomerDTO customer;
	private List<OrderLineDTO> orderLines;
	private BigDecimal total;
	
	public OrderDTO(Long idOrder, CustomerDTO customer, List<OrderLineDTO> orderLines, BigDecimal total) {
		super();
		this.idOrder = idOrder;
		this.customer = customer;
		this.orderLines = orderLines;
		this.total = total;
	}
	
	
}
