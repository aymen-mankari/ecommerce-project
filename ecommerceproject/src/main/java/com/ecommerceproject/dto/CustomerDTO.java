package com.ecommerceproject.dto;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
public class CustomerDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long idCustomer;
	private String fullName;
	private String emailAddress;
	private String city;
	private String phone;
	private ApplicationUserDTO user;

	public CustomerDTO(Long idCustomer, String fullName, String emailAddress, String city, String phone,
			ApplicationUserDTO user) {
		super();
		this.idCustomer = idCustomer;
		this.fullName = fullName;
		this.emailAddress = emailAddress;
		this.city = city;
		this.phone = phone;
		this.user = user;
	}

}
