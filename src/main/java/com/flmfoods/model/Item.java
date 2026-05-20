package com.flmfoods.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Item {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="item_id")
	private int itemId;
	
	@Column(name="item_name")
	private String itemName;
	@Column(nullable = false)
	private double price;
	
	@Column(name="stock_quantity")
	private int stockQuantity;
	private double rating;

	public Item(String itemName, double price, int stockQuantity, double rating) {
		this.itemName = itemName;
		this.price = price;
		this.stockQuantity = stockQuantity;
		this.rating = rating;
	}
}
