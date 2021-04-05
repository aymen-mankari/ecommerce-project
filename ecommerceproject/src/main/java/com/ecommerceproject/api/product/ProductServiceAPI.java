package com.ecommerceproject.api.product;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ecommerceproject.dto.ProductDTO;
import com.ecommerceproject.dto.converters.ProductConverter;
import com.ecommerceproject.entities.Product;
import com.ecommerceproject.service.IProductService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/product")
public class ProductServiceAPI {
	@Autowired
	private IProductService productService;

	@Autowired
	private ProductConverter productConverter;

//	@PostMapping("/create")
//	public void createProduct(@RequestBody ProductDTO product,@RequestParam("imageFile") MultipartFile file) {
//		productService.save(productConverter.convertToBo(product));
//	}

	//MediaType.MULTIPART_FORM_DATA_VALUE
	@PostMapping("/create")
	//@PostMapping(value = "/create", consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE })
	public void createProduct(@RequestParam("product") String product, @RequestParam("image") MultipartFile image) {
		System.out.println(product.toString());
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			ProductDTO productDTO = objectMapper.readValue(product, ProductDTO.class);
			productDTO.setPicByte(image.getBytes());
			productService.save(productConverter.convertToBo(productDTO));
			System.out.println(productDTO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//productService.save(productConverter.convertToBo(product));
	}

	@GetMapping("/getAll")
	public Collection<ProductDTO> getAllProducts() {
		return productConverter.convertToDTOList(productService.getAll());
	}

	@GetMapping("/getOne/{id}")
	public ProductDTO getProduct(@PathVariable(name = "id") Long id) {
		return productConverter.convertToDTO(productService.get(id).get());
	}

	@PutMapping("/update")
	public ProductDTO updateProduct(@RequestBody ProductDTO productDTO) {
		Product product = productConverter.convertToBo(productDTO);
		productService.update(product);
		return null;
	}

	@DeleteMapping("/delete/{id}")
	public void deleteProduct(@PathVariable(name = "id") Long id) {
		productService.delete(id);
	}

	@GetMapping("/search/{keyword}")
	public Collection<ProductDTO> getProductsByKeyword(@PathVariable(name = "keyword") String keyword) {
		List<Product> products = productService.findProductsByDesignation(keyword);
		return productConverter.convertToDTOList(products);
	}

}
