package com.flmfoods.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	
	private static final Logger log=LoggerFactory.getLogger(ItemController.class);
	
	@PostMapping("/add")
	public ResponseEntity<ItemResponseDTO> addItem(@RequestBody AddItemRequestDTO addItemRequestDTO) {
		log.trace("Request recieved to Add an Item in Item Controller");
		
		log.trace("Calling Service from Item Controller");
		 ItemResponseDTO item = itemServiceImpl.addItem(addItemRequestDTO);
		 log.trace("Recieved data from the Item Service");  
		 
		 log.trace("Sending response for Add Item from Item Controller");
		 return new ResponseEntity<>(item, HttpStatus.OK);
	}
	
	@GetMapping()		// "/items" - direct root
	public ResponseEntity<List<ItemResponseDTO>> getAllItems(){
		 List<ItemResponseDTO> allItems = itemServiceImpl.getAllItems();
		 return  ResponseEntity.ok(allItems);
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
