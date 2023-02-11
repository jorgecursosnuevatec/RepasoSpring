package com.jgr.micro.alumno.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.jgr.micro.alumno.entity.Alumno;
import com.jgr.micro.alumno.service.IAlumnoService;

// TODO: Auto-generated Javadoc
/**
 * The Class AlumnoController.
 */
@RestController
public class AlumnoController {

	/** The Constant log. */
	private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(AlumnoController.class);

	/** The alumno service. */
	@Autowired
	IAlumnoService alumnoService;

	/**
	 * Inicializar.
	 */
	@PostConstruct
	public void inicializar() {
		int limite = 4;

		Alumno al;
		List<Alumno> alumnosLista = new ArrayList<>();

		for (int i = 0; i < limite; i++) {
			al = new Alumno();
			al.setNombre("Nombre" + i);
			al.setApellidos("Apellido" + i);
			al.setEmail("Email" + i + "@mail.com");
			alumnoService.save(al);
			alumnosLista.add(al);
		}

	}

	/**
	 * Listar todos.
	 *
	 * @return the response entity
	 */
	@GetMapping
	public ResponseEntity<?> listarTodos() {

		return ResponseEntity.ok(alumnoService.findAll());

	}

	/**
	 * Buscar por id.
	 *
	 * @param idAlumno the id alumno
	 * @return the response entity
	 */
	@GetMapping("id/{idAlumno}")
	public ResponseEntity<?> buscarPorId(@PathVariable Long idAlumno) {

		Optional<Alumno> al = alumnoService.findById(idAlumno);

		if (al.isPresent()) {
			return ResponseEntity.ok(al.get());

		} else {
			return ResponseEntity.notFound().build();
		}

	}

	/**
	 * Find by nombre like ignore case.
	 *
	 * @param nombreAlumno the nombre alumno
	 * @return the response entity
	 */
	@GetMapping("nombre/{nombreAlumno}")
	public ResponseEntity<?> findByNombreLikeIgnoreCase(@PathVariable String nombreAlumno) {

		List<Alumno> al = (List<Alumno>) alumnoService.findByNombreContainsIgnoreCase(nombreAlumno);

		if (al.size() > 0) {
			return ResponseEntity.ok(al);
		}

		return ResponseEntity.notFound().build();

	}

	/**
	 * Actualiza alumno.
	 *
	 * @param al the al
	 * @param id the id
	 * @return the response entity
	 */
	@PutMapping("/{id}")
	public ResponseEntity<?> actualizaAlumno(@RequestBody Alumno al, @PathVariable Long id) {

		Optional<Alumno> o = alumnoService.findById(id);

		if (!o.isPresent()) {
			log.debug("Microservicio Alumno->actualizaAlumno");

			return ResponseEntity.notFound().build();
		}
		Alumno alDb = o.get();

		alDb.setNombre(al.getNombre());
		alDb.setApellidos(al.getApellidos());
		alDb.setEmail(al.getEmail());

		return ResponseEntity.ok().body(alumnoService.save(alDb));

	}

	/**
	 * Busca nombre or apellido.
	 *
	 * @param term the term
	 * @return the response entity
	 */
	@GetMapping("/nombreoapellido/{term}")
	public ResponseEntity<?> buscaNombreOrApellido(@PathVariable String term) {

		List<Alumno> alumnos = (List<Alumno>) alumnoService.buscaNombreOApellido(term);

		if (alumnos.size() > 0) {
			return ResponseEntity.ok().body(alumnos);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

		}
	}

	/**
	 * Find by nombre or apellidos containing ignore case.
	 *
	 * @param nombre   the nombre
	 * @param apellido the apellido
	 * @return the response entity
	 */
	@GetMapping("/nombreoapellidoignoramayusculas/{nombre}/{apellido}")
	public ResponseEntity<?> findByNombreOrApellidosContainingIgnoreCase(@PathVariable String nombre,
			@PathVariable String apellido) {

		List<Alumno> alumnos = (List<Alumno>) alumnoService
				.findByNombreContainingIgnoreCaseOrApellidosContainingIgnoreCase(nombre, apellido);

		if (alumnos.size() > 0) {
			return ResponseEntity.ok().body(alumnos);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

		}

	}

	/**
	 * Ver foto.
	 *
	 * @param id the id
	 * @return the response entity
	 */
	@GetMapping("/uploads/img/{id}")
	public ResponseEntity<?> verFoto(@PathVariable Long id) {

		Optional<Alumno> o = alumnoService.findById(id);

		if (o.isEmpty() || o.get().getFoto() == null) {
			return ResponseEntity.notFound().build();
		}

		Resource imagen = new ByteArrayResource(o.get().getFoto());

		return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imagen);
	}

	/**
	 * Crear con foto.
	 *
	 * @param alumno  the alumno
	 * @param result  the result
	 * @param archivo the archivo
	 * @return the response entity
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@PostMapping("/crear-con-foto")
	public ResponseEntity<?> crearConFoto(@Valid Alumno alumno, BindingResult result,
			@RequestParam MultipartFile archivo) throws IOException {
		if (!archivo.isEmpty()) {
			alumno.setFoto(archivo.getBytes());
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		/*
		 * return super.creaEntidad(alumno, result);
		 */
	}

	/**
	 * Editar con foto.
	 *
	 * @param alumno  the alumno
	 * @param result  the result
	 * @param id      the id
	 * @param archivo the archivo
	 * @return the response entity
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@PutMapping("/editar-con-foto/{id}")
	public ResponseEntity<?> editarConFoto(@Valid Alumno alumno, BindingResult result, @PathVariable Long id,
			@RequestParam MultipartFile archivo) throws IOException {
		/*
		 * if (result.hasErrors()) { return this.validar(result); }
		 * 
		 * Optional<Alumno> o = alumnoService.findById(id);
		 * 
		 * if (o.isEmpty()) { return ResponseEntity.notFound().build(); }
		 * 
		 * Alumno alumnoDb = o.get(); alumnoDb.setNombre(alumno.getNombre());
		 * alumnoDb.setApellidos(alumno.getApellidos());
		 * alumnoDb.setEmail(alumno.getEmail());
		 * 
		 * if (!archivo.isEmpty()) { alumnoDb.setFoto(archivo.getBytes()); }
		 * 
		 * return
		 * ResponseEntity.status(HttpStatus.CREATED).body(alumnoService.save(alumnoDb));
		 */

		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
	
	/**
	 * Guardar Alumno.
	 * para probar MOCKITO antes de incluir el resto de microservicios que tienen el modelo de datos
	 *
	 * @param alumno the alumno
	 * @return the response entity
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@PostMapping("/guardaNormal")
	public ResponseEntity<?> guardarAlumno(@Valid Alumno alumno) throws IOException {
		
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(alumnoService.save(alumno));
	}

	

}
