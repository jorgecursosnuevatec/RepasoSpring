package com.jgr.micro.productos.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.jgr.micro.productos.entity.Producto;
import com.jgr.micro.productos.service.IProductoService;

import jakarta.annotation.PostConstruct;

// TODO: Auto-generated Javadoc
/**
 * The Class ProductoController.
 */
@RestController
public class ProductoController {
	
	/** The producto service. */
	@Autowired
	private IProductoService productoService;
	
	/** The limite carga inicial. */
	private int limiteCargaInicial =5;
	
	/**
	 * Carga inicial.
	 */
	@PostConstruct
	public void cargaInicial() {
		
		Producto prod;
		for (int i =0;i<limiteCargaInicial;i++) {
			prod = new Producto();
			prod.setNombre("Producto"+i);
			prod.setPrecio(new Random().nextDouble()*5+1);
			prod.setCreateAt(new Date());
			productoService.guardarProducto(prod);
		}
		
	}

	/**
	 * Listar.
	 *
	 * @return the list
	 */
	@GetMapping("/listar")
	public List<Producto> listar(){
		return productoService.findAll();
	}
	
	/**
	 * Detalle.
	 *
	 * @param id the id
	 * @return the producto
	 */
	@GetMapping("/ver/{id}")
	public Producto detalle(@PathVariable Long id) {
		return productoService.findById(id);
	}
	
	@GetMapping("/buscarMayorIgual/{precio}")
	public List<Producto> findPorPrecioMayorIgualStreamList(@PathVariable Double precio) {
		return productoService.findPorPrecioMayorIgualStreamList(precio);
	}
	
	
	@GetMapping("/buscarRango/{minPrecio}/{maxPrecio}")
	public List<Producto> findPorPrecioRangoStreamList(@PathVariable Double minPrecio, @PathVariable Double maxPrecio) {
		return productoService.findPorPrecioRangoStreamList(minPrecio,maxPrecio);
	}
	
	

}
