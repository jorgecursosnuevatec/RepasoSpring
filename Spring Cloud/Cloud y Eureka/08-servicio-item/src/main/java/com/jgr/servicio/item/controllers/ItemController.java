package com.jgr.servicio.item.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.jgr.servicio.item.models.Item;
import com.jgr.servicio.item.models.Producto;
import com.jgr.servicio.item.models.service.ItemService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;



// TODO: Auto-generated Javadoc
/**
 * The Class ItemController.
 */
@RestController
public class ItemController {
	
	/** The item service. */
	@Autowired
	@Qualifier("serviceFeign")
	private ItemService itemService;
	
	/** The circuit breaker factory. */
	@Autowired
	private CircuitBreakerFactory circuitBreakerFactory;
	
	
	/** The logger. */
	private final Logger logger = LoggerFactory.getLogger(ItemController.class);
	
	/**
	 * Listar.
	 *
	 * @return the list
	 */
	@GetMapping("/listar")
	public List<Item> listar(){
		return itemService.findAll();
	}
	
	
	/**
	 * Detalle.
	 *
	 * @param id the id
	 * @param cantidad the cantidad
	 * @return the item
	 */
	@GetMapping("/ver/{id}/cantidad/{cantidad}")
	public Item detalle(@PathVariable Long id, @PathVariable Integer cantidad) {
		return itemService.findById(id, cantidad);
	}
	
	/**
	 * Detalle error metodo alternativo.
	 * declaramos por programa un metodo alternativo en caso de error
	 * si da error le pasamos como parametro al metodo alternativo el error
	 * lo configuramos en AppConfigResilience
	 *
	 * @param id the id
	 * @param cantidad the cantidad
	 * @return the item
	 */
	
	
	@GetMapping("/verResilience4j/{id}/cantidad/{cantidad}")
	public Item detalleErrorMetodoAlternativo(@PathVariable Long id, @PathVariable Integer cantidad) {
		
		//le doy un nombre al metodo alternativo a ejecutar en caso de error
		return circuitBreakerFactory.create("metodoAlternativoErrorItemsPrograma")
				.run(()->itemService.findByIdError(id, cantidad)//esto lo hace si ok
						,error->metodoAlternativo(id,cantidad,error));//si hay error hace esto
	}
	
	
	/**
	 * Detalle error circuit breaker.
	 * lo definimos en el properties
	 *
	 * @param id the id
	 * @param cantidad the cantidad
	 * @return the item
	 */
	@CircuitBreaker(name="errorControladoCircuitBreaker",fallbackMethod="metodoAlternativo")
	@TimeLimiter(name="errorControladoCircuitBreaker")
	
	@GetMapping("/verMetodoCircuitBreaker/{id}/cantidad/{cantidad}")
	public Item detalleErrorCircuitBreaker(@PathVariable Long id, @PathVariable Integer cantidad) {
		
		return itemService.findByIdError(id, cantidad);
	}
	
	
	
	/**
	 * Metodo alternativo.
	 *
	 * @param id the id
	 * @param cantidad the cantidad
	 * @param error the error
	 * @return the item
	 */
	public Item metodoAlternativo(Long id, Integer cantidad,Throwable error) {
		
		logger.info("entra en metodo alternativo ItemController->"+error.getLocalizedMessage());
		
	
		Item item = new Item();
		
		Producto producto = new Producto();		
		item.setCantidad(999999999);
		producto.setId((long) 9.9);
		producto.setNombre("Generado en metodo Alternativo error");
		producto.setPrecio(00.00);
		item.setProducto(producto);
		return item;
		
	}
	
	
	/**
	 * Listar parametros sysout.
	 *
	 * @param nombre the nombre
	 * @param token the token
	 * @return the list
	 */
	@GetMapping("/listarParametrosSysout")
	public List<Item> listarParametrosSysout(@RequestParam(name="nombre", required= false) String nombre,
			@RequestHeader(name="token-request", required = false) String token){
//		System.out.println(nombre);
//		System.out.println(token);
		logger.info("ItemController listarParametrosSysout->: " + nombre);
		logger.info("ItemController listarParametrosSysout->: " + token);
		return itemService.findAll();
	}
	

}
