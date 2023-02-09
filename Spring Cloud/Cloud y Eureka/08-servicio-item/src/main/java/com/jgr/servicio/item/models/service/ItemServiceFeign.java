package com.jgr.servicio.item.models.service;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jgr.commons.modelo.datos.models.entity.Producto;
import com.jgr.servicio.item.cliente.feign.ProductoClienteRest;
import com.jgr.servicio.item.models.Item;

/**
 * The Class ItemServiceFeign.
 */
@Service("serviceFeign")
public class ItemServiceFeign implements ItemService {
	
	/** The cliente feign. */
	@Autowired
	private ProductoClienteRest clienteFeign;
	
	/** The logger. */
	private final Logger logger = LoggerFactory.getLogger(ItemServiceFeign.class);

	/**
	 * Find all.
	 *
	 * @return the list
	 */
	@Override
	public List<Item> findAll() {
		return clienteFeign.listar().stream().map(p -> new Item(p, 1)).collect(Collectors.toList());
	}

	/**
	 * Find by id.
	 *
	 * @param id the id
	 * @param cantidad the cantidad
	 * @return the item
	 */
	@Override
	public Item findById(Long id, Integer cantidad) {
		logger.debug("En clienteFeing NO metodo error");
		return new Item(clienteFeign.detalle(id), cantidad);
	}
	
	/**
	 * Find by id error.
	 *
	 * @param id the id
	 * @param cantidad the cantidad
	 * @return the item
	 */
	@Override
	public Item findByIdError(Long id, Integer cantidad) {
		logger.debug("En clienteFeign metodo error");

		return new Item(clienteFeign.detalleError(id), cantidad);
	}

	/**
	 * Alta producto.
	 *
	 * @param producto the producto
	 * @return the producto
	 */
	@Override
	public Producto altaProducto(Producto producto) {
		return clienteFeign.altaProducto(producto);
	}

	/**
	 * Editar.
	 *
	 * @param producto the producto
	 * @param id the id
	 * @return the producto
	 */
	@Override
	public Producto editar(Producto producto, Long id) {
		return clienteFeign.editar(producto, id);
	}

	/**
	 * Eliminar.
	 *
	 * @param id the id
	 */
	@Override
	public void eliminar(Long id) {
		clienteFeign.eliminar(id);
		
	}

}
