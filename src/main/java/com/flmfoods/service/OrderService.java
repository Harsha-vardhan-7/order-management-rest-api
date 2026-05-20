package com.flmfoods.service;

import org.springframework.web.bind.annotation.RequestBody;

import com.flmfoods.dto.OrderResponseDTO;
import com.flmfoods.dto.PlaceOrderRequestDTO;

public interface OrderService {
	public OrderResponseDTO placeOrder(@RequestBody PlaceOrderRequestDTO placeOrderRequestDTO);
}
