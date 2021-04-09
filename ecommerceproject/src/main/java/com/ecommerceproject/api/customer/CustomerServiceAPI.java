package com.ecommerceproject.api.customer;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerceproject.dto.ApplicationUserDTO;
import com.ecommerceproject.dto.CustomerDTO;
import com.ecommerceproject.dto.converters.CustomerConverter;
import com.ecommerceproject.entities.Customer;
import com.ecommerceproject.service.ICustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerServiceAPI {

	@Autowired
	@Qualifier(value = "CustomerServiceImpl")
	private ICustomerService customerService;

	@Autowired
	private CustomerConverter customerConverter;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@PostMapping("/create")
	public void createCustomer(@RequestBody CustomerDTO customer) {
		ApplicationUserDTO user = customer.getUser();
		String uncodedPassword = user.getPassword();
		user.setPassword(passwordEncoder.encode(uncodedPassword));
		customer.setUser(user);
		customerService.saveOrUpdate(customerConverter.convertToBo(customer));
	}

	@GetMapping("/getAll")
	public Collection<CustomerDTO> getAllCustomers() {
		return customerConverter.convertToDTOList(customerService.getAll());
	}

	@GetMapping("/getOne/{id}")
	public CustomerDTO getCustomer(@PathVariable(name = "id") Long id) {
		return customerConverter.convertToDTO(customerService.get(id).get());
	}

	@PutMapping("/update")
	public CustomerDTO updateCustomer(@RequestBody CustomerDTO customerDTO) {
		Customer customer = customerConverter.convertToBo(customerDTO);
		customerService.saveOrUpdate(customer);
		return null;
	}

	@DeleteMapping("/delete/{id}")
	public void deleteCustomer(@PathVariable(name = "id") Long id) {
		customerService.delete(id);
	}
	
	@GetMapping("/findByUsername/{username}")
	public CustomerDTO findByUsername(@PathVariable(name = "username") String username) {
		return customerConverter.convertToDTO(customerService.findByUsername(username));
	}
	
}
