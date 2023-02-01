package com.jgr.servicio.producto.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.jgr.servicio.producto.models.entity.Producto;
import com.jgr.servicio.producto.models.service.IProductoService;


/**
 * The Class ProductoController.
 */
@RestController
public class ProductoController {
	
	/** The env. */
	@Autowired
	private Environment env;
	
	/** The port. */
	@Value("${server.port}")
	private Integer port;
	
	/** The producto service. */
	@Autowired
	private IProductoService productoService;
	
	/**
	 * Listar.
	 *
	 * @return the list
	 */
	@GetMapping("/listar")
	public List<Producto> listar(){
		return productoService.findAll().stream().map(producto ->{
			producto.setPort(Integer.parseInt(env.getProperty("local.server.port")));
			//producto.setPort(port);
			return producto;
		}).collect(Collectors.toList());
	}
	
	/**
	 * Detalle.
	 *
	 * @param id the id
	 * @return the producto
	 */
	@GetMapping("/ver/{id}")
	public Producto detalle(@PathVariable Long id) {
		Producto producto = productoService.findById(id);
		producto.setPort(Integer.parseInt(env.getProperty("local.server.port")));
		//producto.setPort(port);
		
		/*
		 * try {
			Thread.sleep(2000L);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		return producto;
	}
	
	/**
	 * Find por precio mayor igual stream list.
	 *
	 * @param precio the precio
	 * @return the list
	 */
	@GetMapping("/buscarMayorIgual/{precio}")
	public List<Producto> findPorPrecioMayorIgualStreamList(@PathVariable Double precio) {
		return productoService.findPorPrecioMayorIgualStreamList(precio);
	}


	/**
	 * Find por precio rango stream list.
	 *
	 * @param minPrecio the min precio
	 * @param maxPrecio the max precio
	 * @return the list
	 */
	@GetMapping("/buscarRango/{minPrecio}/{maxPrecio}")
	public List<Producto> findPorPrecioRangoStreamList(@PathVariable Double minPrecio, @PathVariable Double maxPrecio) {
		return productoService.findPorPrecioRangoStreamList(minPrecio,maxPrecio);
	}


}