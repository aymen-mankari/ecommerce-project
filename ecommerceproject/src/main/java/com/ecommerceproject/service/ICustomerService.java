package com.ecommerceproject.service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import com.ecommerceproject.entities.Customer;

public interface ICustomerService {
	    
    Optional<Customer> get(Long id);
    List<Customer> getAll();
    Customer save(Customer customer);
    Customer update(Customer customer);
    void delete(Long id);
}
