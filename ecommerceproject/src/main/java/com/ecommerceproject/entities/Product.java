package com.ecommerceproject.entities;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.TableGenerator;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@NoArgsConstructor
@Data
@ToString
public class Product {
	
	@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_generator")
	@SequenceGenerator(name="product_generator", sequenceName = "product_seq",initialValue = 100)
	private Long id;
	@Column(unique = true, updatable = false , nullable = false)
	private String reference;
	@Column(unique = true, nullable = false)
	private String designation;
	private String description;
	@Column(nullable = false)
	private BigDecimal price;
	@Column(nullable = false)
	private int stockQuantity;
	
	public Product(String reference, String designation,String description, BigDecimal price, int stockQuantity) {
		this.reference = reference;
		this.designation = designation;
		this.description = description;
		this.price = price;
		this.stockQuantity = stockQuantity;
	}
	
	public Product(Long id, String reference, String designation,String description, BigDecimal price, int stockQuantity) {
		this.id=id;
		this.reference = reference;
		this.designation = designation;
		this.description = description;
		this.price = price;
		this.stockQuantity = stockQuantity;
	}
}
