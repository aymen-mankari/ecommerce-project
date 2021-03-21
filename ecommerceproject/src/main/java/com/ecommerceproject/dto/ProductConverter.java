package com.ecommerceproject.dto;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.ecommerceproject.entities.Product;

@Component(value = "ProductConverter")
public class ProductConverter implements IConverter<ProductDTO, Product> {

	@Override
	public ProductDTO convertToDTO(Product o) {
		return new ProductDTO(o.getId(), o.getReference(), o.getDesignation(), o.getDescription(), o.getPrice(),
				o.getStockQuantity());
	}

	@Override
	public List<ProductDTO> convertToDTOList(List<Product> list) {
		return list.stream().map(this::convertToDTO).collect(Collectors.toList());
	}

	@Override
	public Product convertToBo(ProductDTO o) {
		return new Product(o.getIdProduct(), o.getReference(), o.getDesignation(), o.getDescription(), o.getPrice(),
				o.getStockQuantity());
	}

	@Override
	public List<Product> convertToBoList(List<ProductDTO> list) {
		return list.stream().map(this::convertToBo).collect(Collectors.toList());
	}

}
