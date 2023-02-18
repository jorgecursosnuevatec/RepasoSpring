package com.jgr.micro.alumno.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.validation.Valid;

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

import com.jgr.controller.generico.ControladorGenerico;
import com.jgr.micro.alumno.entity.Alumno;
import com.jgr.micro.alumno.service.IAlumnoService;



/**
 * The Class AlumnoController.
 * HEREDA del generico controller que es el que tiene los metodos comunes, leer todos,buscar por id,borrar e insertar
 * 
 * como parametros el generico controller recibe el objeto y la capa de servicio
 * 
 * como HEREDA de ControladorGenerico no tenemos que inyectar la dependencia de la capa de servicio,
 * va a tomar la definida en ControladorGenerico 
 * SUSTITUIMOS LA INSTANCIA DE LA CAPA DE SERVICIO QUE TENEMOS EN ESTE MICROSERVICIO POR LA QUE HEREDAMOS
 * DE GENERICO-SERVICE, que se llama  
 * 
 */
@RestController
public class AlumnoController extends ControladorGenerico <Alumno,IAlumnoService>{

	/** The Constant log. */
	private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(AlumnoController.class);

	
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
			servicioDeGenericoService.save(al);
			alumnosLista.add(al);
		}

	}

	
	
	@GetMapping("nombre/{nombreAlumno}")
	public ResponseEntity<?> findByNombreLikeIgnoreCase(@PathVariable String nombreAlumno) {

		List<Alumno> al = (List<Alumno>) servicioDeGenericoService.findByNombreContainsIgnoreCase(nombreAlumno);

		if (al.size() > 0) {
			return ResponseEntity.ok(al);
		}

		return ResponseEntity.notFound().build();

	}

	
	@PutMapping("/{id}")
	public ResponseEntity<?> actualizaAlumno(@RequestBody Alumno al, @PathVariable Long id) {

		Optional<Alumno> o = servicioDeGenericoService.findById(id);

		if (!o.isPresent()) {
			log.debug("Microservicio Alumno->actualizaAlumno");

			return ResponseEntity.notFound().build();
		}
		Alumno alDb = o.get();

		alDb.setNombre(al.getNombre());
		alDb.setApellidos(al.getApellidos());
		alDb.setEmail(al.getEmail());

		return ResponseEntity.ok().body(servicioDeGenericoService.save(alDb));

	}

	
	@GetMapping("/nombreoapellido/{term}")
	public ResponseEntity<?> buscaNombreOrApellido(@PathVariable String term) {

		List<Alumno> alumnos = (List<Alumno>) servicioDeGenericoService.buscaNombreOApellido(term);

		if (alumnos.size() > 0) {
			return ResponseEntity.ok().body(alumnos);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

		}
	}

	
	@GetMapping("/nombreoapellidoignoramayusculas/{nombre}/{apellido}")
	public ResponseEntity<?> findByNombreOrApellidosContainingIgnoreCase(@PathVariable String nombre,
			@PathVariable String apellido) {

		List<Alumno> alumnos = (List<Alumno>) servicioDeGenericoService
				.findByNombreContainingIgnoreCaseOrApellidosContainingIgnoreCase(nombre, apellido);

		if (alumnos.size() > 0) {
			return ResponseEntity.ok().body(alumnos);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

		}

	}

	
	@GetMapping("/uploads/img/{id}")
	public ResponseEntity<?> verFoto(@PathVariable Long id) {

		Optional<Alumno> o = servicioDeGenericoService.findById(id);

		if (o.isEmpty() || o.get().getFoto() == null) {
			return ResponseEntity.notFound().build();
		}

		Resource imagen = new ByteArrayResource(o.get().getFoto());

		return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imagen);
	}

	
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

	
	@PutMapping("/editar-con-foto/{id}")
	public ResponseEntity<?> editarConFoto(@Valid Alumno alumno, BindingResult result, @PathVariable Long id,
			@RequestParam MultipartFile archivo) throws IOException {
		/*
		 * if (result.hasErrors()) { return this.validar(result); }
		 * 
		 * Optional<Alumno> o = servicioDeGenericoService.findById(id);
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
		 * ResponseEntity.status(HttpStatus.CREATED).body(servicioDeGenericoService.save(alumnoDb));
		 */

		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
	
	

}
