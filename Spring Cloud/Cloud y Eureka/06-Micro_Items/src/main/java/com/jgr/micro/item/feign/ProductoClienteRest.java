package com.jgr.micro.item.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.jgr.micro.item.models.Producto;


/**
 * The Interface ProductoClienteRest.
 */
@FeignClient(name = "servicio-productos")
public interface  ProductoClienteRest {

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

}



