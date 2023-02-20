package com.jgr.servicio.curso.sql.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jgr.controller.generico.ControladorGenerico;
import com.jgr.model.sql.alumno.entity.Alumno;
import com.jgr.servicio.curso.sql.entity.Curso;
import com.jgr.servicio.curso.sql.service.ICursoService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class CursoController extends ControladorGenerico<Curso, ICursoService> {

	/** The Constant log. */
	private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(CursoController.class);

	/**
	 * Inicializar.
	 */
//	@PostConstruct
	public void inicializar() {
		int limite = 4;

		Alumno al;
		Curso cur;
		List<Alumno> alumnosLista = new ArrayList<>();

		for (int i = 0; i < limite; i++) {

			cur = new Curso();
			cur.setNombreCurso("Curso" + i);
			log.debug("Antes del primer save");

			servicioDeGenericoService.save(cur);
			log.debug("DESPUES del primer save");

			for (int j = 0; j < limite; j++) {
				al = new Alumno();
				al.setNombre("Nombre" + i + j);
				al.setApellidos("Apellido" + i + j);
				al.setEmail("Email" + i + j + "@mail.com");
//				alumnosLista.add(al);
				log.debug("Antes del add");
				cur.addAlumno(al);
				log.debug("Despues del add");

			}
			// cur.addAlumno(alumnosLista);
			// asigno cada alumno al curso
//			alumnosLista.forEach(cur::addAlumno);

			log.debug("despues del bucle");
			servicioDeGenericoService.save(cur);
			log.debug("despues del bucle2");
		}

	}
	
	
	@Value("${config.balanceador.test}")
	private String balanceadorTest;
	
	@GetMapping("/balanceador-test")
	public ResponseEntity<?> balanceadorTest() {
		Map<String, Object> response = new HashMap<String, Object>();
		response.put("balanceador", balanceadorTest);
		response.put("cursos", servicioDeGenericoService.findAll());
		return ResponseEntity.ok(response);
	}
	
	
	@PutMapping("/{id}")
	public ResponseEntity<?> editar(@Valid @RequestBody Curso curso, BindingResult result, @PathVariable Long id){
		
		if(result.hasErrors()) {
			return this.validar(result);
		}
		
	    Optional<Curso> o = this.servicioDeGenericoService.findById(id);
	    if(!o.isPresent()) {
	    	return ResponseEntity.notFound().build();
	    }
	    Curso dbCurso = o.get();
	    dbCurso.setNombreCurso(curso.getNombreCurso());
	    return ResponseEntity.status(HttpStatus.CREATED).body(this.servicioDeGenericoService.save(dbCurso));
	}
	
	
	@PutMapping("/{id}/eliminar-alumno")
	public ResponseEntity<?> eliminarAlumno(@RequestBody Alumno alumno, @PathVariable Long id){
	    Optional<Curso> o = this.servicioDeGenericoService.findById(id);
	    if(!o.isPresent()) {
	    	return ResponseEntity.notFound().build();
	    }
	    Curso dbCurso = o.get();
	    
	    dbCurso.removeAlumno(alumno);
	    
	    return ResponseEntity.status(HttpStatus.CREATED).body(this.servicioDeGenericoService.save(dbCurso));
	}
	
	/*
	@GetMapping("/alumno/{id}")
	public ResponseEntity<?> buscarPorAlumnoId(@PathVariable Long id){
		Curso curso = service.findCursoByAlumnoId(id);
		
		if(curso != null) {
			
			List<Long> examenesIds = (List<Long>) servicioDeGenericoService.obtenerExamenesIdsConRespuestasAlumno(id);
			
			List<Examen> examenes = curso.getExamenes().stream().map(examen -> {
				if(examenesIds.contains(examen.getId())) {
					examen.setRespondido(true);
				}
				return examen;
			}).collect(Collectors.toList());
			
			curso.setExamenes(examenes);
		}
		return ResponseEntity.ok(curso);
	}
	@PutMapping("/{id}/asignar-examenes")
	public ResponseEntity<?> asignarExamenes(@RequestBody List<Examen> examenes, @PathVariable Long id){
	    Optional<Curso> o = this.service.findById(id);
	    if(!o.isPresent()) {
	    	return ResponseEntity.notFound().build();
	    }
	    Curso dbCurso = o.get();
	    
	    examenes.forEach(e -> {
	    	dbCurso.addExamen(e);
	    });
	    
	    return ResponseEntity.status(HttpStatus.CREATED).body(this.service.save(dbCurso));
	}
	
	@PutMapping("/{id}/eliminar-examen")
	public ResponseEntity<?> eliminarExamen(@RequestBody Examen examen, @PathVariable Long id){
	    Optional<Curso> o = this.service.findById(id);
	    if(!o.isPresent()) {
	    	return ResponseEntity.notFound().build();
	    }
	    Curso dbCurso = o.get();
	    
	    dbCurso.removeExamen(examen);
	    
	    return ResponseEntity.status(HttpStatus.CREATED).body(this.service.save(dbCurso));
	}
	*/
	

}
