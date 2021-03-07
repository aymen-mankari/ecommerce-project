package com.ecommerceproject.entities;

import javax.persistence.Column;
import javax.persistence.Entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
public class Customer extends ApplicationUser {
	private String fullName;
	
	private String emailAddress;
	private String city;
	private String phone;

	public Customer(String fullName, String emailAddress,String city,String phone) {
		this.fullName = fullName;
		this.emailAddress = emailAddress;
		this.city = city;
		this.phone = phone;
	}
	
}
