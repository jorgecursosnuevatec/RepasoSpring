package com.jgr.servicio.producto.models.service;

import java.util.List;

import com.jgr.servicio.producto.models.entity.Producto;

// TODO: Auto-generated Javadoc
/**
 * The Interface IProductoService.
 */
public interface IProductoService {

	/**
	 * Find all.
	 *
	 * @return the list
	 */
	public List<Producto> findAll();
	
	/**
	 * Find by id.
	 *
	 * @param id the id
	 * @return the producto
	 */
	public Producto findById(Long id);
	
	/**
	 * Find por precio mayor igual.
	 *
	 * @param precio the precio
	 * @return the list
	 */
	public List<Producto> findPorPrecioMayorIgualStreamList(Double precio);

	/**
	 * Find por precio rango stream list.
	 *
	 * @param minPrecio the min precio
	 * @param maxPrecio the max precio
	 * @return the list
	 */
	public List<Producto> findPorPrecioRangoStreamList(Double minPrecio,Double maxPrecio);


	/**
	 * Guardar producto.
	 *
	 * @param producto the producto
	 * @return the producto
	 */
	public Producto guardarProducto(Producto producto);


	/**
	 * Guardar lista productos.
	 *
	 * @param productos the productos
	 * @return the list
	 */
	public List<Producto> guardarListaProductos(List<Producto> productos); 
}
