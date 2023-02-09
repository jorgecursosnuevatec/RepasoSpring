package com.jgr.servicio.item.models.service;

import java.util.List;

import com.jgr.commons.modelo.datos.models.entity.Producto;
import com.jgr.servicio.item.models.Item;


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
	
	/**
	 * Find by id error.
	 *
	 * @param id the id
	 * @param cantidad the cantidad
	 * @return the item
	 */
	public Item findByIdError(Long id, Integer cantidad);
	
	
	/**
	 * Alta producto.
	 *
	 * @param producto the producto
	 * @return the producto
	 */
	public Producto altaProducto(Producto producto);
	
	/**
	 * Editar.
	 *
	 * @param producto the producto
	 * @param id the id
	 * @return the producto
	 */
	public Producto editar(Producto producto,Long id);
	
	/**
	 * Eliminar.
	 *
	 * @param id the id
	 * @return the producto
	 */
	public void eliminar(Long id);
}
