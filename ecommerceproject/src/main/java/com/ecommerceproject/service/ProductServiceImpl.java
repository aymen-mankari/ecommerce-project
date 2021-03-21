package com.ecommerceproject.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerceproject.dao.ProductRepository;
import com.ecommerceproject.entities.Product;

@Service(value = "ProductServiceImpl")
public class ProductServiceImpl implements IProductService {

	@Autowired
	private ProductRepository productRepository;

	@Override
	public Optional<Product> get(Long id) {
		return productRepository.findById(id);
	}

	@Override
	public List<Product> getAll() {
		return productRepository.findAll();
	}

	@Override
	public Product save(Product product) {
		return productRepository.save(product);
	}

	@Override
	public Product update(Product product) {
		Product updatedProduct = productRepository.save(product);
		return updatedProduct;
	}

	@Override
	public void delete(Long id) {
		productRepository.deleteById(id);

	}

	@Override
	public List<Product> findProductsByDesignation(String keyword) {
		return productRepository.findByDesignationIgnoreCaseContaining(keyword);
	}

}
