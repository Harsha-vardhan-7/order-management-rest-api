package com.flmfoods.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table (name="orders")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "order_id")
	private int orderId;
	
	@Column(name="total_quantity")
	private int totalQuantity;
	
	@Column(name="total_price")
	private double finalOrderPrice;

	@OneToMany(mappedBy = "order", cascade= CascadeType.ALL )
	List<OrderItems> orderItems;

	public Order(int totalQuantity, int finalOrderPrice, List<OrderItems> orderItems) {
		super();
		this.totalQuantity = totalQuantity;
		this.finalOrderPrice = finalOrderPrice;
		this.orderItems = orderItems;
	}
}
