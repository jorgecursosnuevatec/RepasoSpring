package com.jgr.servicio.producto.models.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jgr.commons.modelo.datos.models.entity.Producto;
import com.jgr.servicio.producto.models.repository.ProductoRepository;


/**
 * The Class ProductoServiceImpl.
 */
@Service
public class ProductoServiceImpl implements IProductoService{

	/** The producto repository. */
	@Autowired
	private ProductoRepository productoRepository;
	
	/**
	 * Find all.
	 *
	 * @return the list
	 */
	@Override
	@Transactional(readOnly = true)
	public List<Producto> findAll() {
		return (List<Producto>) productoRepository.findAll();
	}

	/**
	 * Find by id.
	 *
	 * @param id the id
	 * @return the producto
	 */
	@Override
	@Transactional(readOnly = true)
	public Producto findById(Long id) {
		return productoRepository.findById(id).orElse(null);
	}

	/**
	 * Find por precio mayor igual.
	 *
	 * @param precio the precio
	 * @return the list
	 */
	@Override
	@Transactional(readOnly = true)
	public List<Producto> findPorPrecioMayorIgualStreamList(Double precio) {

		List<Producto>  listaProductos = new ArrayList<>();
		listaProductos =(List<Producto>) productoRepository.findAll();
		return listaProductos.stream()		
		.filter(p->((Producto) p).getPrecio()>=precio)
		.collect(Collectors.toList());

	}

	/**
	 * Find por precio rango stream list.
	 *
	 * @param minPrecio the min precio
	 * @param maxPrecio the max precio
	 * @return the list
	 */
	@Override
	@Transactional(readOnly = true)
	public List<Producto> findPorPrecioRangoStreamList(Double minPrecio, Double maxPrecio) {
		List<Producto>  listaProductos = new ArrayList<>();
		listaProductos =(List<Producto>) productoRepository.findAll();
				System.out.println("precio min"+minPrecio);
		return listaProductos.stream()		
		.filter(p->((Producto) p).getPrecio()>=minPrecio && p.getPrecio()<=maxPrecio)
		.collect(Collectors.toList());
	}

	/**
	 * Guardar producto.
	 *
	 * @param producto the producto
	 * @return the producto
	 */
	@Override
	@Transactional
	public Producto guardarProducto(Producto producto) {

		return productoRepository.save(producto);
	}

	/**
	 * Guardar lista productos.
	 *
	 * @param productos the productos
	 * @return the list
	 */
	@Override
	@Transactional
	public List<Producto> guardarListaProductos(List<Producto> productos) {
		return (List<Producto>) productoRepository.saveAll(productos);
	}

	/**
	 * Delete by id.
	 *
	 * @param id the id
	 */
	@Override
	@Transactional
	public void deleteById(Long id){
		productoRepository.deleteById(id);
		
	}
	
	
}
