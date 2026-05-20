package com.flmfoods.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flmfoods.dto.OrderItemRequestDTO;
import com.flmfoods.dto.OrderItemsResponseDTO;
import com.flmfoods.dto.OrderResponseDTO;
import com.flmfoods.dto.PlaceOrderRequestDTO;
import com.flmfoods.exception.InsufficientStockException;
import com.flmfoods.exception.ItemNotFoundException;
import com.flmfoods.model.Item;
import com.flmfoods.model.Order;
import com.flmfoods.model.OrderItems;
import com.flmfoods.repository.ItemRepository;
import com.flmfoods.repository.OrderRepository;
import com.flmfoods.service.OrderService;

import jakarta.transaction.Transactional;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	ItemRepository itemRepository;

	@Autowired
	OrderRepository orderRepository;

	
	@Override
	@Transactional
	public OrderResponseDTO placeOrder(PlaceOrderRequestDTO placeOrderRequestDTO) {

		List<OrderItemRequestDTO> orderItemRequestList = placeOrderRequestDTO.getOrderItemReqeustList();

		Order order = new Order();

		List<OrderItems> orderItemsList = new ArrayList<>();

		double finalOrderPrice = 0;
		int totalQuantity = 0;

		for (OrderItemRequestDTO orderItemRequestDTO : orderItemRequestList) {
			int orderingItemId = orderItemRequestDTO.getItemId();
			Item item = itemRepository.findById(orderingItemId).orElseThrow( ()-> new ItemNotFoundException("Item not Found With Id: "+ orderingItemId)
					);

			OrderItems orderItems = new OrderItems();

			orderItems.setItem(item);

			int quantity = orderItemRequestDTO.getQuantity();
			int stockQuantity = item.getStockQuantity();

			if (quantity > stockQuantity) {
				throw new InsufficientStockException("Insufficient stock for the Item with Id: "+ orderingItemId);
			}
			orderItems.setQuantity(quantity);
			totalQuantity += quantity;

			item.setStockQuantity(stockQuantity - quantity);

			double itemPrice = item.getPrice() * orderItemRequestDTO.getQuantity();

			finalOrderPrice += itemPrice;

			orderItems.setUnitPrice(item.getPrice());
			orderItems.setSubTotal(itemPrice);

			orderItems.setOrder(order);

			orderItemsList.add(orderItems);

			itemRepository.save(item);
		}

		order.setOrderItems(orderItemsList);
		order.setTotalQuantity(totalQuantity);
		order.setFinalOrderPrice(finalOrderPrice);

		Order savedOrder = orderRepository.save(order);

		OrderResponseDTO orderResponseDTO = new OrderResponseDTO();

		List<OrderItemsResponseDTO> orderItemResponselist = new ArrayList<>();

		List<OrderItems> orderItems = savedOrder.getOrderItems();

		for (OrderItems orderItem : orderItems) {
			OrderItemsResponseDTO orderItemsResponseDTO = new OrderItemsResponseDTO();

			orderItemsResponseDTO.setItemName(orderItem.getItem().getItemName());
			orderItemsResponseDTO.setPrice(orderItem.getUnitPrice());
			orderItemsResponseDTO.setQuantity(orderItem.getQuantity());
			orderItemsResponseDTO.setSubTotal(orderItem.getUnitPrice()* orderItem.getQuantity());

			orderItemResponselist.add(orderItemsResponseDTO);
		}

		orderResponseDTO.setOrderId(savedOrder.getOrderId());
		orderResponseDTO.setTotalPrice(savedOrder.getFinalOrderPrice());
		orderResponseDTO.setOrderItemResponse(orderItemResponselist);

		return orderResponseDTO;
	}

}
