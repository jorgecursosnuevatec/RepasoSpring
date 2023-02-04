package com.jgr.servicio.producto.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


import com.jgr.servicio.producto.models.entity.Producto;
import com.jgr.servicio.producto.models.service.IProductoService;


// TODO: Auto-generated Javadoc
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
	
	
	private final Logger logger = LoggerFactory.getLogger(ProductoController.class);
	
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
		
		
		logger.debug("endetalleProducto");
		Producto producto = productoService.findById(id);
		producto.setPort(Integer.parseInt(env.getProperty("local.server.port")));
		//producto.setPort(port);
		
		return producto;
	}
	
	@GetMapping("/verError/{id}")
	public Producto detalleError(@PathVariable Long id) {
		Producto producto = productoService.findById(id);
		producto.setPort(Integer.parseInt(env.getProperty("local.server.port")));
		//producto.setPort(port);
		
		logger.debug("en detalleErrorProducto");
		
		if(id==1) {
			try {
				Thread.sleep(2000L);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}			
		}
		
		if(id!=null & id!=1) {
			logger.error("en detalleErrorProducto");
		throw new IllegalStateException("Error provocado en controller producto para probar circuitbreaker");
		}
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
	
	
	
	/**
	 * Alta producto.
	 *
	 * @param producto the producto
	 * @return the producto
	 */
	@PostMapping("/alta")
	@ResponseStatus(HttpStatus.CREATED)
	public Producto altaProducto(@RequestBody Producto producto) {
		return productoService.guardarProducto(producto);
	}
	
	
	/**
	 * Alta lista productos.
	 *
	 * @param productos the productos
	 * @return the list
	 */
	@PostMapping("/altaListaProductos")
	@ResponseStatus(HttpStatus.CREATED)
	public List<Producto> altaListaProductos(@RequestBody List<Producto> productos) {
		return productoService.guardarListaProductos(productos);
	}

	
	/**
	 * Editar.
	 *
	 * @param producto the producto
	 * @param id the id
	 * @return the producto
	 */
	@PutMapping("/editar/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Producto editar(@RequestBody Producto producto, @PathVariable Long id) {
		Producto productoDb = productoService.findById(id);
		
		productoDb.setNombre(producto.getNombre());
        productoDb.setPrecio(producto.getPrecio());
        
        return productoService.guardarProducto(productoDb);
	}
	
	/**
	 * Eliminar.
	 *
	 * @param id the id
	 */
	@DeleteMapping("/eliminar/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void eliminar(@PathVariable Long id) {
		productoService.deleteById(id);
	}

}
