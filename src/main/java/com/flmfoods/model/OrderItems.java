package com.flmfoods.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class OrderItems {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "orderItem_id")
	private int orderItemId;
	private int quantity;

	@Column(name = "unit_price")
	private double unitPrice;

	@Column(name = "subTotal_price")
	private double subTotal;

	@ManyToOne
	@JoinColumn(name = "item_id")
	private Item item;

	@ManyToOne
	@JoinColumn(name = "order_id")
	private Order order;

	public OrderItems(int quantity, double unitPrice, double subTotal, Item item, Order order) {
		super();
		this.quantity = quantity;
		this.unitPrice = unitPrice;
		this.subTotal = subTotal;
		this.item = item;
		this.order = order;
	}

	public OrderItems(int quantity, double unitPrice) {
		super();
		this.quantity = quantity;
		this.unitPrice = unitPrice;
	}

}
