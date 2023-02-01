package com.jgr.micro.item.service;

import java.util.List;

import com.jgr.micro.item.models.Item;


/**
 * The Interface ItemService.
 */
public interface ItemService {

	/**
	 * Find all.
	 *
	 * @return the list
	 */
	public List<Item> findAll();
	
	/**
	 * Find by id.
	 *
	 * @param id the id
	 * @param cantidad the cantidad 
	 * @return the item
	 */
	public Item findById(Long id, Integer cantidad);
}
