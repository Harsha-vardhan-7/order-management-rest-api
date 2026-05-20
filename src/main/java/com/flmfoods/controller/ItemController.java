package com.flmfoods.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.flmfoods.dto.AddItemRequestDTO;
import com.flmfoods.dto.ItemResponseDTO;
import com.flmfoods.dto.UpdateRequestDTO;
import com.flmfoods.service.impl.ItemServiceImpl;

@RestController
@RequestMapping("/items")
public class ItemController {

	@Autowired
	ItemServiceImpl itemServiceImpl;
	
	@PostMapping("/add")
	public ItemResponseDTO addItem(@RequestBody AddItemRequestDTO addItemRequestDTO) {
		return itemServiceImpl.addItem(addItemRequestDTO);
	}
	
	@GetMapping()		// "/items" - direct root
	public List<ItemResponseDTO> getAllItems(){
		return itemServiceImpl.getAllItems();
	}
	
	@GetMapping("/byId/{itemId}")		//If it is path variable we should mention in annotation
	public ItemResponseDTO getItemById(@PathVariable int itemId) {
		return itemServiceImpl.getItemById(itemId);
	}
	
	@GetMapping("/filterByPrice")		//required=false is to make parameter as an optional
	public List<ItemResponseDTO> getItemGreaterThanPrice(@RequestParam(required = false) Integer price){
		return itemServiceImpl.getItemGreaterThanPrice(price);
	}
	
	@DeleteMapping("/delete/{itemId}")
	public String deleteItem(@PathVariable int itemId) {
		return itemServiceImpl.deleteItem(itemId);
	}
	
	@PutMapping("/update/{itemId}")
	public ItemResponseDTO updateItem(@RequestBody UpdateRequestDTO updateRequestDTO, @PathVariable int itemId) {
		return itemServiceImpl.updateItem(updateRequestDTO, itemId);
	}
}
