package com.jgr.servicio.item.models.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.jgr.servicio.item.models.Item;
import com.jgr.servicio.item.models.Producto;

// TODO: Auto-generated Javadoc
/**
 * The Class ItemServiceImpl.
 */
@Service("serviceRestTemplate")
public class ItemServiceImpl implements ItemService {

	/** The cliente rest. */
	@Autowired
	private RestTemplate clienteRest;
	
	/** The logger. */
	private final Logger logger = LoggerFactory.getLogger(ItemServiceImpl.class);
	
	/**
	 * Find all.
	 *
	 * @return the list
	 */
	@Override
	public List<Item> findAll() {
		List<Producto> productos = Arrays.asList(clienteRest.getForObject("http://servicio-productos/listar", Producto[].class));
		
		return productos.stream().map(p -> new Item(p, 1)).collect(Collectors.toList());
	}

	/**
	 * Find by id.
	 *
	 * @param id the id
	 * @param cantidad the cantidad
	 * @return the item
	 */
	@Override
	public Item findById(Long id, Integer cantidad) {
		
		
		Map<String, String> pathVariables = new HashMap<String, String>();
		pathVariables.put("id", id.toString());
		Producto producto = clienteRest.getForObject("http://servicio-productos/ver/{id}", Producto.class, pathVariables);
		return new Item(producto, cantidad);
	}

	/**
	 * Find by id error.
	 *
	 * @param id the id
	 * @param cantidad the cantidad
	 * @return the item
	 */
	@Override
	public Item findByIdError(Long id, Integer cantidad) {
		
		Map<String, String> pathVariables = new HashMap<String, String>();
		pathVariables.put("id", id.toString());
		Producto producto = clienteRest.getForObject("http://servicio-productos/verError/{id}", Producto.class, pathVariables);
		return new Item(producto, cantidad);
	}

	/**
	 * Alta producto.
	 * Conectamos con el microservicio productos para que realice el alta
	 * le indicamos la url,el metodo y le pasamos el producto que hemos recuperado
	 *
	 * @param producto the producto
	 * @return the producto
	 */
	@Override
	public Producto altaProducto(Producto producto) {
		
		
		HttpEntity<Producto> body = new HttpEntity<Producto>(producto);
		
		
		
		ResponseEntity<Producto> response= //donde vamos a guardar los datos de salida
		clienteRest.exchange("http://servicio-productos/alta",
				HttpMethod.POST, //metodo que vamos a usar
				body, //parametros,en este caso un httpentity				
				Producto.class );//tipo de dato que le vamos a pasar
		
		return response.getBody();//devolvemos el producto que recuperamos de la llamada
	}

	/**
	 * Editar.
	 *
	 * @param producto the producto
	 * @param id the id
	 * @return the producto
	 */
	@Override
	public Producto editar(Producto producto, Long id) {
		
		//convertimos el producto a a httpentity
		HttpEntity<Producto> body = new HttpEntity<Producto>(producto);
		
		//el valor de id lo pasamos en un map
		Map<String, String> pathVariables = new HashMap<String, String>();
		pathVariables.put("id", id.toString());
		
		ResponseEntity<Producto> response= //donde vamos a guardar los datos de salida
		clienteRest.exchange("http://servicio-productos/editar/{id}",
				HttpMethod.PUT, //metodo que vamos a usar
				body, //parametros,en este caso un httpentity				
				Producto.class, //tipo de dato que vamos a obtener
				pathVariables //variable id
				);//tipo de dato que vamos a obtener
		
		return response.getBody();//devolvemos el producto que recuperamos de la llamada
	}

	/**
	 * Eliminar.
	 *
	 * @param id the id
	 */
	@Override
	public void eliminar(Long id) {
		//el valor de id lo pasamos en un map
				Map<String, String> pathVariables = new HashMap<String, String>();
				pathVariables.put("id", id.toString());
		
		clienteRest.delete("http://servicio-productos/eliminar/{id}", //ruta a la que va a ir en servicio productos
				pathVariables);//valor del id ,lo convertimos a un map
	}

}
