package com.flmfoods.service;

import java.util.List;

import com.flmfoods.dto.AddItemRequestDTO;
import com.flmfoods.dto.ItemResponseDTO;
import com.flmfoods.dto.UpdateRequestDTO;

public interface ItemService {

	public ItemResponseDTO addItem(AddItemRequestDTO itemRequestDTO);
	
	public List<ItemResponseDTO> getAllItems();
	
	public ItemResponseDTO getItemById(int id);
	
	public List<ItemResponseDTO> getItemGreaterThanPrice(Integer price);
	
	public String deleteItem(int itemId);
	
	public ItemResponseDTO updateItem(UpdateRequestDTO updateRequestDTO, int itemId);

	
}
