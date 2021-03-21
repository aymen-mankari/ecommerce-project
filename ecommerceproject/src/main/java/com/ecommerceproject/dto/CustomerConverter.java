package com.ecommerceproject.dto;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ecommerceproject.entities.Customer;

@Component(value = "CustomerConverter")
public class CustomerConverter implements IConverter<CustomerDTO, Customer> {

	@Autowired
	private ApplicationUserConverter applicationUserConverter;

	@Override
	public CustomerDTO convertToDTO(Customer o) {
		return new CustomerDTO(o.getId(), o.getFullName(), o.getEmailAddress(), o.getCity(), o.getPhone(),
				applicationUserConverter.convertToDTO(o.getApplicationUser()));
	}

	@Override
	public List<CustomerDTO> convertToDTOList(List<Customer> list) {
		return list.stream().map(this::convertToDTO).collect(Collectors.toList());
	}

	@Override
	public Customer convertToBo(CustomerDTO o) {
		return new Customer(o.getIdCustomer(),o.getFullName(), o.getEmailAddress(), o.getCity(), o.getPhone(),
				applicationUserConverter.convertToBo(o.getApplicationUserDTO()));
	}

	@Override
	public List<Customer> convertToBoList(List<CustomerDTO> list) {
		return list.stream().map(this::convertToBo).collect(Collectors.toList());
	}

}
