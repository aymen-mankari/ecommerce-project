package com.ecommerceproject.dto;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.ecommerceproject.entities.ApplicationUser;
import com.ecommerceproject.entities.Customer;

@Component(value = "ApplicationUserConverter")
public class ApplicationUserConverter implements IConverter<ApplicationUserDTO, ApplicationUser> {

	@Override
	public ApplicationUserDTO convertToDTO(ApplicationUser o) {
		return new ApplicationUserDTO(o.getId(), o.getUsername(), o.getPassword(), o.isAdmin());
	}

	@Override
	public List<ApplicationUserDTO> convertToDTOList(List<ApplicationUser> list) {
		return list.stream().map(this::convertToDTO).collect(Collectors.toList());
	}

	@Override
	public ApplicationUser convertToBo(ApplicationUserDTO o) {
		return new ApplicationUser(o.getUsername(), o.getPassword(), o.isAdmin());
	}

	@Override
	public List<ApplicationUser> convertToBoList(List<ApplicationUserDTO> list) {
		return list.stream().map(this::convertToBo).collect(Collectors.toList());
	}

}
