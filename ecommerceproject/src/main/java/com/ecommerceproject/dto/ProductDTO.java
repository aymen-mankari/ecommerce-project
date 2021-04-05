package com.ecommerceproject.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
public class ProductDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long idProduct;
	private String reference;
	private String designation;
	private String description;
	private BigDecimal price;
	private int stockQuantity;
	private byte[] picByte;

	public ProductDTO(Long idProduct, String reference, String designation, String description, BigDecimal price,
			int stockQuantity, byte[] picByte) {
		super();
		this.idProduct = idProduct;
		this.reference = reference;
		this.designation = designation;
		this.description = description;
		this.price = price;
		this.stockQuantity = stockQuantity;
		this.picByte=picByte;
	}

}
