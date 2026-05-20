package com.flmfoods.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemsResponseDTO {

	private String itemName;
	private int quantity;
	private double price;
	private double subTotal;
	
}
