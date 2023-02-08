package com.jgr.servicio.item.controllers;
import org.springframework.core.env.Environment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.jgr.servicio.item.models.Item;
import com.jgr.servicio.item.models.Producto;
import com.jgr.servicio.item.models.service.ItemService;




/**
 * The Class ItemController.
 * se llama internamente con feign o con servicio rest dependiendo de cual de los item service
 * usamos al microservicio productos
 */

@RestController
@RefreshScope//para actualizar componentes con @Value,Environment...
public class ItemController {
	
	/** The item service. */
	@Autowired
	//@Qualifier("serviceFeign")
	@Qualifier("serviceRestTemplate")
	private ItemService itemService;
	
	
	/** The texto config.
	//RECOGEMOS LA PROPIEDAD DEFINIDA EN EL PROPERTIES
	 * 
	 */
	@Value("${configuracion.texto.properties}")
	
	private String textoConfig;
	
	
	/** The env. */
	@Autowired	
    private Environment env;

	/** The entorno. */
	@Value("${spring.profiles.active}")
	private String entorno;
	
	
	
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
	
	
	
	@GetMapping("/verResilience4j/{id}/cantidad/{cantidad}")
	public Item detalleErrorMetodoAlternativo(@PathVariable Long id, @PathVariable Integer cantidad) {
		
		//le doy un nombre al metodo alternativo a ejecutar en caso de error
		return circuitBreakerFactory.create("metodoAlternativoErrorItemsPrograma")
				.run(()->itemService.findByIdError(id, cantidad)//esto lo hace si ok
						,error->metodoAlternativo(id,cantidad,error));//si hay error hace esto
	}
	 */
	
	
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
	
	
	/**
	 * Obtener configuracion del serviciodesde git.
	 * El puerto lo obtenemos como parametro,tambien con el @Value
	 *
	 * @param puerto the puerto
	 * @return the list
	 */
	@GetMapping("/obtenerConfig")
	public ResponseEntity<?> obtenerConfiguracion(@Value("${server.port}") String puerto){
				
		Map<String,String> mapaRetorno = new HashMap<>();
		mapaRetorno.put("Configuracion", textoConfig);
		mapaRetorno.put("Puerto",puerto);
		mapaRetorno.put("Entorno", entorno);

		
		
		//añadimos el entorno en el que esta
		if( env.getDefaultProfiles().length>0) {
			for (String p :env.getDefaultProfiles()) {
				mapaRetorno.put("Por defecto",p);				
			}
		}
		
		if( env.getActiveProfiles().length>0) {
			for (String p :env.getActiveProfiles()) {
				mapaRetorno.put("Perfiles",p);				
			}
		}
		
		
		logger.debug("env.getActiveProfiles()"+ env.getActiveProfiles().toString());
		mapaRetorno.forEach((k,v)->logger.debug("clave->"+ k + " valor->"+v));
		
		//ordeno el mapa
		
		List<Entry<String, String>> list = new ArrayList<>(mapaRetorno.entrySet());
		
        list.sort(Entry.comparingByValue());
        
        Map<String, String> result = new LinkedHashMap<>();
        
        for (Entry<String, String> entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }

		return new ResponseEntity <Map<String,String>>(result,HttpStatus.OK);
		
	}
	
	/**
	 * Crear.
	 *
	 * @param producto the producto
	 * @return the producto
	 */
	@PostMapping("/crear")
	@ResponseStatus(HttpStatus.CREATED)
	public Producto crear(@RequestBody Producto producto) {
		return itemService.altaProducto(producto);
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
		return itemService.editar(producto, id);
	}
	
	/**
	 * Eliminar.
	 *
	 * @param id the id
	 */
	@DeleteMapping("/eliminar/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void eliminar(@PathVariable Long id) {
		itemService.eliminar(id);
	}
	
	

}
