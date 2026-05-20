package com.flmfoods.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponseDTO {

	private int orderId;
	private double totalPrice;
	
	List<OrderItemsResponseDTO> orderItemResponse;
}
