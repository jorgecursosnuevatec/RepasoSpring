package com.jgr.micro.item.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.jgr.micro.item.models.Item;
import com.jgr.micro.item.service.ItemService;

/**
 * The Class ItemController.
 */
@RestController
public class ItemController {
	
	
	/** The item service. */
	@Autowired
	@Qualifier("serviceFeign")
	private ItemService itemService;
	
	/**
	 * Listar.
	 *
	 * @return the list
	 */
	@GetMapping("/listar")
	public List<Item> listar(){
		return itemService.findAll();
	}
	
	/**
	 * Detalle.
	 *
	 * @param id the id
	 * @param cantidad the cantidad
	 * @return the item
	 */
	@GetMapping("/ver/{id}/cantidad/{cantidad}")
	public Item detalle(@PathVariable Long id, @PathVariable Integer cantidad) {
		return itemService.findById(id, cantidad);
	}
	

}
