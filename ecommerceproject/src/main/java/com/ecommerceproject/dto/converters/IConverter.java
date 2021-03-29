package com.ecommerceproject.dto.converters;

import java.util.List;

public interface IConverter<T,U> {
	public T convertToDTO(U o);
	public List<T> convertToDTOList(List<U> list);
	public U convertToBo(T o);
	public List<U> convertToBoList(List<T> list);
}
