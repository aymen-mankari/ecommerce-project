package com.ecommerceproject.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@NoArgsConstructor
@Data
@ToString
public class Customer {
	@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_generator")
	@SequenceGenerator(name="customer_generator", sequenceName = "customer_seq",initialValue = 200)
	private Long id;
	private String fullName;
	@Column(unique = true)
	private String emailAddress;
	private String city;
	private String phone;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "appUser_id")
	private ApplicationUser applicationUser;
	
	public Customer(String fullName, String emailAddress,String city,String phone,ApplicationUser applicationUser) {
		this.fullName = fullName;
		this.emailAddress = emailAddress;
		this.city = city;
		this.phone = phone;
		this.applicationUser = applicationUser;
	}
	
	public Customer(Long id, String fullName, String emailAddress,String city,String phone,ApplicationUser applicationUser) {
		this.id = id;
		this.fullName = fullName;
		this.emailAddress = emailAddress;
		this.city = city;
		this.phone = phone;
		this.applicationUser = applicationUser;
	}

}
