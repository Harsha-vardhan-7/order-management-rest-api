package com.flmfoods.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flmfoods.dto.OrderResponseDTO;
import com.flmfoods.dto.PlaceOrderRequestDTO;
import com.flmfoods.service.impl.OrderServiceImpl;

@RestController
@RequestMapping("orders")
public class OrderController {

	@Autowired
	OrderServiceImpl orderServiceImpl;
	
	@PostMapping("/placeOrder")
	public OrderResponseDTO placeOrder(@RequestBody PlaceOrderRequestDTO placeOrderRequestDTO) {
		return orderServiceImpl.placeOrder(placeOrderRequestDTO);
	}
}
