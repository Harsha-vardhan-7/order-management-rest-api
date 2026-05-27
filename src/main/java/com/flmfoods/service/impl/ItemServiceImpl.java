package com.flmfoods.service.impl;

import java.util.List;

import org.apache.logging.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flmfoods.dto.AddItemRequestDTO;
import com.flmfoods.dto.ItemResponseDTO;
import com.flmfoods.dto.UpdateRequestDTO;
import com.flmfoods.exception.ItemNotFoundException;
import com.flmfoods.model.Item;
import com.flmfoods.repository.ItemRepository;
import com.flmfoods.service.ItemService;

@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	ItemRepository itemRepo;
	
//	LoggerFactory.getLogger()

	private ItemResponseDTO mapToDTO(Item item) {
		ItemResponseDTO responseDTO = new ItemResponseDTO();
		BeanUtils.copyProperties(item, responseDTO);
		return responseDTO;
	}

	public ItemResponseDTO addItem(AddItemRequestDTO itemRequestDTO) {
		Item item = new Item();

		BeanUtils.copyProperties(itemRequestDTO, item);

		Item savedItems = itemRepo.save(item);
		ItemResponseDTO response = new ItemResponseDTO();

		BeanUtils.copyProperties(savedItems, response);

		return response;
	}

	public List<ItemResponseDTO> getAllItems() {
		List<Item> allItems = itemRepo.findAll();

		return allItems.stream().map(item -> mapToDTO(item)).toList();
	}

	@Override
	public ItemResponseDTO getItemById(int id) {
		Item itemById = itemRepo.findById(id)
				.orElseThrow(() -> new ItemNotFoundException("Item Not Found with the Id: " + id));

		ItemResponseDTO response = new ItemResponseDTO();
		BeanUtils.copyProperties(itemById, response);

		return response;
	}

	@Override
	public List<ItemResponseDTO> getItemGreaterThanPrice(Integer price) {
		List<Item> allItems = itemRepo.findAll();

		if (price == null) {
			return allItems.stream().map(item -> {
				ItemResponseDTO response = new ItemResponseDTO();
				BeanUtils.copyProperties(item, response);
				return response;
			}).toList();
		}

		return allItems.stream().filter(item -> item.getPrice() > price).map(item -> mapToDTO(item)).toList();

	}

	@Override
	public String deleteItem(int itemId) {
		Item item = itemRepo.findById(itemId)
				.orElseThrow(() -> new ItemNotFoundException("Item Not Found with the Id: " + itemId));

		itemRepo.delete(item);

		return "Item with Id " + itemId + " has been succesfully deleted";
	}

	@Override
	public ItemResponseDTO updateItem(UpdateRequestDTO updateRequestDTO, int itemId) {
		Item item = itemRepo.findById(itemId)
				.orElseThrow(() -> new ItemNotFoundException("Item not found with the Id: " + itemId));

		BeanUtils.copyProperties(updateRequestDTO, item);

		itemRepo.save(item);

		ItemResponseDTO responseDTO = new ItemResponseDTO();

		BeanUtils.copyProperties(item, responseDTO);

		return responseDTO;
	}

}
