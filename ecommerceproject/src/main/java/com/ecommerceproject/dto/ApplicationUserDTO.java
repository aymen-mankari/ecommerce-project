package com.ecommerceproject.dto;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
public class ApplicationUserDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long idUser;
	private String username;
	private String password;
	private boolean isAdmin;
	
	public ApplicationUserDTO(Long idUser, String username, String password, boolean isAdmin) {
		super();
		this.idUser = idUser;
		this.username = username;
		this.password = password;
		this.isAdmin = isAdmin;
	}
	
}
