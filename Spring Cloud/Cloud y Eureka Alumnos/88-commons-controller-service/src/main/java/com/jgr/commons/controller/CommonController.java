package com.jgr.commons.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.jgr.commons.service.CommonService;


/**
 * The Class CommonController.
 * No le ponemos el @RestController porque lo va
 * a tener el que herede
 * 
 * E->entity,objeto que le pasamos S->service,capa de servicio que va a recibir,
 * que hereda de generico-service,es una INTERFAZ
 * 
 * @param <E> the element type
 * @param <S> the generic type
 */
public class CommonController<E, S extends CommonService<E>> {

	/** The servicio.
	 PROTECTED PARA QUE PUEDA USARLO EL QUE LO HEREDE  */
	@Autowired
	protected S service;
	
	/**
	 * Listar.
	 *
	 * @return the response entity
	 */
	@GetMapping
	public ResponseEntity<?> listar(){
		return ResponseEntity.ok().body(service.findAll());
	}
	
	/**
	 * Listar.
	 *
	 * @param pageable the pageable
	 * @return the response entity
	 */
	@GetMapping("/pagina")
	public ResponseEntity<?> listar(Pageable pageable){
		return ResponseEntity.ok().body(service.findAll(pageable));
	}
	
	/**
	 * Ver.
	 *
	 * @param id the id
	 * @return the response entity
	 */
	@GetMapping("/{id}")
	public ResponseEntity<?> ver(@PathVariable Long id){
		Optional<E> o = service.findById(id);
		if(o.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(o.get());
	}
	
	/**
	 * Crear.
	 *
	 * @param entity the entity
	 * @param result the result
	 * @return the response entity
	 */
	@PostMapping
	public ResponseEntity<?> crear(@Valid @RequestBody E entity, BindingResult result){
		
		if(result.hasErrors()) {
			return this.validar(result);
		}
		E entityDb = service.save(entity);
		return ResponseEntity.status(HttpStatus.CREATED).body(entityDb);
	}
	
	/**
	 * Eliminar.
	 *
	 * @param id the id
	 * @return the response entity
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<?> eliminar(@PathVariable Long id){
		service.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
	/**
	 * Validar.
	 * Generamos JSON a partir de un mapa con los errores producidos en la validacion
	 *
	 * @param result the result
	 * @return the response entity
	 */
	protected ResponseEntity<?> validar(BindingResult result){
		Map<String, Object> errores = new HashMap<>();
		result.getFieldErrors().forEach(err -> {
			errores.put(err.getField(), " El campo " + err.getField() + " " + err.getDefaultMessage());
		});
		return ResponseEntity.badRequest().body(errores);
	}
}
