
package com.jgr.controller.generico;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.jgr.servicio.generico.service.IServiceGenerico;

/**
 * The Class ControladorGenerico. No le ponemos el @RestController porque lo va
 * a tener el que herede
 * 
 * E->entity,objeto que le pasamos S->service,capa de servicio que va a recibir,
 * que hereda de generico-service,es una INTERFAZ
 *
 * @param <E> the element type
 * @param <S> the generic type
 */
public class ControladorGenerico<E, S extends IServiceGenerico<E>> {

	/** The servicio.
	 PROTECTED PARA QUE PUEDA USARLO EL QUE LO HEREDE  */
	@Autowired
	protected S servicioDeGenericoService;

	/**
	 * Listar todos.
	 *
	 * @return the response entity
	 */
	@GetMapping
	public ResponseEntity<?> listarTodos() {

		List<E> lista = (List<E>) servicioDeGenericoService.findAll();

		if (lista.size() > 0) {
			return ResponseEntity.ok(lista);
		}

		return ResponseEntity.notFound().build();

	}

	/**
	 * Buscar por id.
	 *
	 * @param id the id
	 * @return the response entity
	 */
	@GetMapping("id/{id}")
	public ResponseEntity<?> buscarPorId(@PathVariable Long id) {

		Optional<E> al = servicioDeGenericoService.findById(id);

		if (al.isPresent()) {
			return ResponseEntity.ok(al.get());

		} else {
			return ResponseEntity.notFound().build();
		}

	}

	/**
	 * Guardar entity.
	 *
	 * @param entity the entity
	 * @return the response entity
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@PostMapping("/guardaNormal")
	public ResponseEntity<?> guardarEntity(@RequestBody E entity) throws IOException {

		System.out.println("REPOSITORIO GUARDO ALUMNO" + entity);

		return ResponseEntity.status(HttpStatus.CREATED).body(servicioDeGenericoService.save(entity));
	}

	/**
	 * Eliminar por id.
	 *
	 * @param id the id
	 * @return the response entity
	 */
	@DeleteMapping("/{id}")

	public ResponseEntity<?> eliminarPorId(@PathVariable Long id) {

		servicioDeGenericoService.deleteById(id);

		return ResponseEntity.noContent().build();

	}

}
