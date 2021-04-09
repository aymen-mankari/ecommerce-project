package com.ecommerceproject.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerceproject.dao.CustomerRepository;
import com.ecommerceproject.entities.Customer;

@Service(value = "CustomerServiceImpl")
public class CustomerServiceImpl implements ICustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public Optional<Customer> get(Long id) {
		return customerRepository.findById(id);
	}

	@Override
	public List<Customer> getAll() {
		return customerRepository.findAll();
	}

	@Override
	public Customer saveOrUpdate(Customer customer) {
		return customerRepository.save(customer);
	}

	@Override
	public void delete(Long id) {
		customerRepository.deleteById(id);

	}

	@Override
	public Customer findByUsername(String username) {
		return customerRepository.findByApplicationUserUsername(username);
	}
}
