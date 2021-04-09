package com.ecommerceproject.entities;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.TableGenerator;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@NoArgsConstructor
@Data
@ToString
public class OrderLine {
	@Id
	// @GeneratedValue(strategy = GenerationType.IDENTITY)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "orderline_generator")
	@SequenceGenerator(name = "orderline_generator", sequenceName = "orderline_seq", initialValue = 2000)
	private Long id;
	@Column(nullable = false)
	private int quantity;
	//@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH })
	@ManyToOne(cascade = { CascadeType.ALL })
	@JoinColumn(name = "order_id")
	private Order order;
	@OneToOne(cascade = { CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH })
	@JoinColumn(name = "product_id")
	private Product product;

	public OrderLine(int quantity, Order order, Product product) {
		super();
		this.quantity = quantity;
		this.order = order;
		this.product = product;
	}
	
	public OrderLine(Long id, int quantity, Product product) {
		super();
		this.id= id;
		this.quantity = quantity;
		this.product = product;
	}
	
	public OrderLine(long id, int quantity, Order order, Product product) {
		super();
		this.id= id;
		this.quantity = quantity;
		this.order = order;
		this.product = product;
	}

//	public boolean addProduct(Product tempProduct) {
//		boolean availableQuantity = false;
//		if (this.quantity <= tempProduct.getStockQuantity()) {
//			this.product = tempProduct;
//			int newStockQuantity = this.product.getStockQuantity() - this.quantity;
//			this.product.setStockQuantity(newStockQuantity);
//			availableQuantity = true;
//		} else {
//			System.out.println("[ " + " the provided quatity is greather than the stock quantity for the : "
//					+ tempProduct.getReference() + " ]");
//		}
//		return availableQuantity;
//	}

	public Product addProduct(Product tempProduct) {
		Product updatedProduct = null;
		if (this.quantity <= tempProduct.getStockQuantity()) {
			this.product = tempProduct;
			int newStockQuantity = this.product.getStockQuantity() - this.quantity;
			this.product.setStockQuantity(newStockQuantity);
			updatedProduct = this.product;
		} else {
			System.out.println("[ " + " the provided quatity is greather than the stock quantity for the : "
					+ tempProduct.getReference() + " ]");
		}
		return updatedProduct;
	}

}
