package com.flmfoods.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//Already order placed

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlaceOrderRequestDTO {
	List<OrderItemRequestDTO> orderItemReqeustList;
}
