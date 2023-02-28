package com.jgr.micro.cursos.sql.controllers;

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

import com.jgr.commons.controller.CommonController;
import com.jgr.commons.modelo.alumnos.Alumno;
import com.jgr.commons.modelo.asig.exam.preg.Examen;
import com.jgr.micro.cursos.sql.models.entity.Curso;
import com.jgr.micro.cursos.sql.services.CursoService;

// TODO: Auto-generated Javadoc
/**
 * The Class CursoController.
 */
@RestController
public class CursoController extends CommonController<Curso, CursoService>{

	/** The balanceador test. */
	@Value("${config.balanceador.test}")
	private String balanceadorTest;
	
	/**
	 * Balanceador test.
	 *
	 * @return the response entity
	 */
	@GetMapping("/balanceador-test")
	public ResponseEntity<?> balanceadorTest() {
		Map<String, Object> response = new HashMap<String, Object>();
		response.put("balanceador", balanceadorTest);
		response.put("cursos", service.findAll());
		return ResponseEntity.ok(response);
	}
	
	

	/**
	 * Editar.
	 *
	 * @param curso the curso
	 * @param result the result
	 * @param id the id
	 * @return the response entity
	 */
	@PutMapping("/{id}")
	public ResponseEntity<?> editar(@Valid @RequestBody Curso curso, BindingResult result, @PathVariable Long id){
		
		if(result.hasErrors()) {
			return this.validar(result);
		}
		
	    Optional<Curso> o = this.service.findById(id);
	    if(!o.isPresent()) {
	    	return ResponseEntity.notFound().build();
	    }
	    Curso dbCurso = o.get();
	    dbCurso.setNombre(curso.getNombre());
	    return ResponseEntity.status(HttpStatus.CREATED).body(this.service.save(dbCurso));
	}
	
	/**
	 * Asignar alumnos.
	 *
	 * @param alumnos the alumnos
	 * @param id the id
	 * @return the response entity
	 */
	@PutMapping("/{id}/asignar-alumnos")
	public ResponseEntity<?> asignarAlumnos(@RequestBody List<Alumno> alumnos, @PathVariable Long id){
	    Optional<Curso> o = this.service.findById(id);
	    if(!o.isPresent()) {
	    	return ResponseEntity.notFound().build();
	    }
	    Curso dbCurso = o.get();
	    
	    alumnos.forEach(a -> {
	    	dbCurso.addAlumno(a);
	    });
	    
	    return ResponseEntity.status(HttpStatus.CREATED).body(this.service.save(dbCurso));
	}
	
	/**
	 * Eliminar alumno.
	 *
	 * @param alumno the alumno
	 * @param id the id
	 * @return the response entity
	 */
	@PutMapping("/{id}/eliminar-alumno")
	public ResponseEntity<?> eliminarAlumno(@RequestBody Alumno alumno, @PathVariable Long id){
	    Optional<Curso> o = this.service.findById(id);
	    if(!o.isPresent()) {
	    	return ResponseEntity.notFound().build();
	    }
	    Curso dbCurso = o.get();
	    
	    dbCurso.removeAlumno(alumno);
	    
	    return ResponseEntity.status(HttpStatus.CREATED).body(this.service.save(dbCurso));
	}
	
	/**
	 * Buscar por alumno id.
	 *
	 * @param id the id
	 * @return the response entity
	 * 
	 * obtenemos datos del alumno y examenes respondidos conectando con feign con el microservicio
	 * respuestas
	 */
	@GetMapping("/alumno/{id}")
	public ResponseEntity<?> buscarPorAlumnoId(@PathVariable Long id){
		Curso curso = service.findCursoByAlumnoId(id);
		System.out.println("buscarPorAlumnoId"+curso);
		
		if(curso != null) {
			
			List<Long> examenesIds = (List<Long>) service.obtenerExamenesIdsConRespuestasAlumno(id);
			
			List<Examen> examenes = curso.getExamenes().stream().map(examen -> {
				if(examenesIds.contains(examen.getId())) {
					examen.setRespondido(true);
					System.out.println("Examen respondido"+examen.getId());
				}
				return examen;
			}).collect(Collectors.toList());
			
			curso.setExamenes(examenes);
		}
		return ResponseEntity.ok(curso);
	}
	
	/**
	 * Asignar examenes.
	 *
	 * @param examenes the examenes
	 * @param id the id
	 * @return the response entity
	 */
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
	
	/**
	 * Eliminar examen.
	 *
	 * @param examen the examen
	 * @param id the id
	 * @return the response entity
	 */
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
}
