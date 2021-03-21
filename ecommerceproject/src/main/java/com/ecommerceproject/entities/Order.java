package com.ecommerceproject.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity(name = "OrderTable")
@Table
@NoArgsConstructor
@Data
@ToString
public class Order {

	@Id
	// @GeneratedValue(strategy = GenerationType.IDENTITY)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_generator")
	@SequenceGenerator(name = "order_generator", sequenceName = "order_seq", initialValue = 1000)
	private Long id;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "customer_id")
	private Customer customer;
	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
	private List<OrderLine> orderLines;
	@CreationTimestamp
	private LocalDateTime createdAt;
	@UpdateTimestamp
	private LocalDateTime updatedAt;
	private BigDecimal total;

	public Order(Customer customer, List<OrderLine> orderLines) {
		super();
		this.customer = customer;
		this.orderLines = orderLines;
	}
	
	public Order(Long id,Customer customer, List<OrderLine> orderLines, BigDecimal total) {
		super();
		this.id = id;
		this.customer = customer;
		this.orderLines = orderLines;
		this.total = total;
	}

	public Double addOrderLinesAndCalculTotal(List<OrderLine> orderLines) {
		Double total = 0.0;
		if (!orderLines.isEmpty()) {
			this.setOrderLines(orderLines);
			total = this.orderLines.stream().mapToDouble(e -> e.getProduct().getPrice().doubleValue() * e.getQuantity())
					.sum();
		}
		return total;
	}

//	public void addOrderLines(OrderLine orderLine) {
//		if(orderLines == null) {
//			orderLines = new ArrayList<>();
//		}
//		orderLines.add(orderLine);
//		orderLine.setOrder(this);
//	}

}
