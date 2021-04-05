package com.ecommerceproject.dto.converters;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

import org.springframework.stereotype.Component;

import com.ecommerceproject.dto.ProductDTO;
import com.ecommerceproject.entities.Product;

@Component(value = "ProductConverter")
public class ProductConverter implements IConverter<ProductDTO, Product> {

	@Override
	public ProductDTO convertToDTO(Product o) {
		return new ProductDTO(o.getId(), o.getReference(), o.getDesignation(), o.getDescription(), o.getPrice(),
				o.getStockQuantity(), decompressBytes(o.getPicByte()));
	}

	@Override
	public List<ProductDTO> convertToDTOList(List<Product> list) {
		return list.stream().map(this::convertToDTO).collect(Collectors.toList());
	}

	@Override
	public Product convertToBo(ProductDTO o) {
		return new Product(o.getIdProduct(), o.getReference(), o.getDesignation(), o.getDescription(), o.getPrice(),
				o.getStockQuantity(), compressBytes(o.getPicByte()));
	}

	@Override
	public List<Product> convertToBoList(List<ProductDTO> list) {
		return list.stream().map(this::convertToBo).collect(Collectors.toList());
	}

	// compress the image bytes before storing it in the database
	public static byte[] compressBytes(byte[] data) {

		Deflater deflater = new Deflater();
		deflater.setInput(data);
		deflater.finish();
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);

		byte[] buffer = new byte[1024];

		while (!deflater.finished()) {
			int count = deflater.deflate(buffer);
			outputStream.write(buffer, 0, count);
		}
		try {
			outputStream.close();
		} catch (IOException e) {
		}
		System.out.println("Compressed Image Byte Size - " + outputStream.toByteArray().length);
		return outputStream.toByteArray();
	}

	// uncompress the image bytes before returning it to the angular application
	public static byte[] decompressBytes(byte[] data) {

		Inflater inflater = new Inflater();
		inflater.setInput(data);
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
		byte[] buffer = new byte[1024];
		try {
			while (!inflater.finished()) {
				int count = inflater.inflate(buffer);
				outputStream.write(buffer, 0, count);
			}
			outputStream.close();
		} catch (IOException ioe) {
		} catch (DataFormatException e) {
		}
		return outputStream.toByteArray();
	}
}
