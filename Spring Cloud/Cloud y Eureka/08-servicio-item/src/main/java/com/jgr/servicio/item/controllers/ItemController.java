package com.jgr.servicio.item.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.jgr.servicio.item.models.Item;
import com.jgr.servicio.item.models.Producto;
import com.jgr.servicio.item.models.service.ItemService;


/**
 * The Class ItemController.
 */
@RestController
public class ItemController {
	
	/** The item service. */
	@Autowired
	@Qualifier("serviceFeign")
	private ItemService itemService;
	
	
	private final Logger logger = LoggerFactory.getLogger(ItemController.class);
	
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
	
	/**
	 * Metodo alternativo.
	 *
	 * @param id the id
	 * @param cantidad the cantidad
	 * @return the item
	 */
	public Item metodoAlternativo(Long id, Integer cantidad) {
		Item item = new Item();
		Producto producto = new Producto();
		
		item.setCantidad(cantidad);
		producto.setId(id);
		producto.setNombre("Camara Sony");
		producto.setPrecio(500.00);
		item.setProducto(producto);
		return item;
	}
	
	
	@GetMapping("/listarParametrosSysout")
	public List<Item> listarParametrosSysout(@RequestParam(name="nombre", required= false) String nombre,
			@RequestHeader(name="token-request", required = false) String token){
//		System.out.println(nombre);
//		System.out.println(token);
		logger.info("ItemController listarParametrosSysout->: " + nombre);
		logger.info("ItemController listarParametrosSysout->: " + token);
		return itemService.findAll();
	}
	

}
