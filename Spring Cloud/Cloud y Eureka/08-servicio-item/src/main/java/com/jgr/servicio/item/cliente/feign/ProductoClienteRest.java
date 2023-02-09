package com.jgr.servicio.item.cliente.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.jgr.commons.modelo.datos.models.entity.Producto;

/**
 * The Interface ProductoClienteRest.
 * Como es un cliente feign lo que hacemos es invocar directamente el metodo del controlador de productos
 * para que realice la operacion
 */
@FeignClient(name="servicio-productos")
public interface ProductoClienteRest {
	
	/**
	 * Listar.
	 *
	 * @return the list
	 */
	@GetMapping("/listar")
	public List<Producto> listar();
	
	/**
	 * Detalle.
	 *
	 * @param id the id
	 * @return the producto
	 */
	@GetMapping("/ver/{id}")
	public Producto detalle(@PathVariable Long id);
	
	/**
	 * Detalle error.
	 *
	 * @param id the id
	 * @return the producto
	 */
	@GetMapping("/verError/{id}")
	public Producto detalleError(@PathVariable Long id);
	
	@DeleteMapping("/eliminar/{id}")
	public void eliminar(@PathVariable Long id);
	
	@PostMapping("/alta")
	public Producto altaProducto(@RequestBody Producto producto);
	
	@PutMapping("/editar/{id}")
	public Producto editar(@RequestBody Producto producto, @PathVariable Long id);
	
	
	

}
