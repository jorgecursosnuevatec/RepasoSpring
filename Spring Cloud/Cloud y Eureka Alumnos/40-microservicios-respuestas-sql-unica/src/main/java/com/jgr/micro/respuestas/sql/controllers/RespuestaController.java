package com.jgr.micro.respuestas.sql.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jgr.micro.respuestas.sql.models.entity.Respuesta;
import com.jgr.micro.respuestas.sql.services.RespuestaService;


/**
 * The Class RespuestaController.
 */
@RestController
public class RespuestaController {

	/** The service. */
	@Autowired
	private RespuestaService service;
	
	/**
	 * Crear.
	 *
	 * @param respuestas the respuestas
	 * @return the response entity
	 */
	@PostMapping
	public ResponseEntity<?> crear(@RequestBody Iterable<Respuesta> respuestas){
		Iterable<Respuesta> respuestasDb = service.saveAll(respuestas);
		return ResponseEntity.status(HttpStatus.CREATED).body(respuestasDb);
	}
	
	/**
	 * Obtener respuestas por alumno por examen.
	 *
	 * @param alumnoId the alumno id
	 * @param examenId the examen id
	 * @return the response entity
	 */
	@GetMapping("/alumno/{alumnoId}/examen/{examenId}")
	public ResponseEntity<?> obtenerRespuestasPorAlumnoPorExamen(@PathVariable Long alumnoId, @PathVariable Long examenId){
		Iterable<Respuesta> respuestas = service.findRespuestaByAlumnoByExamen(alumnoId, examenId);
		return ResponseEntity.ok(respuestas);
	}
	
	/**
	 * Obtener examenes ids con respuestas alumno.
	 *
	 * @param alumnoId the alumno id
	 * @return the response entity
	 */
	@GetMapping("/alumno/{alumnoId}/examenes-respondidos")
	public ResponseEntity<?> obtenerExamenesIdsConRespuestasAlumno(@PathVariable Long alumnoId){
		Iterable<Long> examenesIds = service.findExamenesIdsConRespuestasByAlumno(alumnoId);
		return ResponseEntity.ok(examenesIds);
	}
}
