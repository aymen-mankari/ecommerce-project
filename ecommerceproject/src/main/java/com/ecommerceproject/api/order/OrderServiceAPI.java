package com.ecommerceproject.api.order;

import java.time.LocalDate;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerceproject.dto.OrderDTO;
import com.ecommerceproject.dto.converters.OrderConverter;
import com.ecommerceproject.entities.Order;
import com.ecommerceproject.service.IOrderService;

@RestController
@RequestMapping("/order")
public class OrderServiceAPI {

	@Autowired
	private IOrderService orderService;

	@Autowired
	private OrderConverter orderConverter;

	@PostMapping("/create")
	public void createOrder(@RequestBody OrderDTO order) {
		orderService.save(orderConverter.convertToBo(order));
	}

	@GetMapping("/getAll")
	public Collection<OrderDTO> getAllOrders() {
		return orderConverter.convertToDTOList(orderService.getAll());
	}

	@GetMapping("/getOne/{id}")
	public OrderDTO getOrder(@PathVariable(name = "id") Long id) {
		return orderConverter.convertToDTO(orderService.get(id).get());
	}

	@PutMapping("/update")
	public OrderDTO updateOrder(@RequestBody OrderDTO orderDTO) {
		Order order = orderConverter.convertToBo(orderDTO);
		orderService.update(order);
		return null;
	}

	@DeleteMapping("/delete/{id}")
	public void deleteOrder(@PathVariable(name = "id") Long id) {
		orderService.delete(id);
	}

	@GetMapping("/findByCustomer/{id}")
	public Collection<OrderDTO> getOrdersByCustomer(@PathVariable(name = "id") Long id) {
		return orderConverter.convertToDTOList(orderService.getOrdersByCustomer(id));
	}

	@GetMapping("/findByCustomer/{startDate}/{endDate")
	public Collection<OrderDTO> findOrdersBetweenTwoDates(@PathVariable(name = "startDate") LocalDate startDate,
			@PathVariable(name = "endDate") LocalDate endDate) {
		return orderConverter.convertToDTOList(orderService.findOrdersBetweenTwoDates(startDate, endDate));
		
	}
}
