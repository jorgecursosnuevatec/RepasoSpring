package com.jgr.micro.alumno.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.jgr.micro.alumno.entity.Alumno;
import com.jgr.micro.alumno.service.IAlumnoService;



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
			al.setApellido("Apellido" + i);
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

}
