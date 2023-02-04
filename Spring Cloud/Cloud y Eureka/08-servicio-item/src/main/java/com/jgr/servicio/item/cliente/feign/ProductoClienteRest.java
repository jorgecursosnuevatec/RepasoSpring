package com.jgr.servicio.item.cliente.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.jgr.servicio.item.models.Producto;

@FeignClient(name="servicio-productos")
public interface ProductoClienteRest {
	
	@GetMapping("/listar")
	public List<Producto> listar();
	
	@GetMapping("/ver/{id}")
	public Producto detalle(@PathVariable Long id);
	
	@GetMapping("/verError/{id}")
	public Producto detalleError(@PathVariable Long id);
	

}
