package com.ecommerceproject.service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import com.ecommerceproject.entities.Customer;
import com.ecommerceproject.entities.Product;

public interface IProductService {
	    
    Optional<Product> get(Long id);
    List<Product> getAll();
    Product save(Product product);
    Product update(Product product);
    void delete(Long id);
    List<Product> findProductsByDesignation(String keyword);
}
