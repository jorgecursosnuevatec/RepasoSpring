package com.jgr.micro.respuestas.sql.services;

import com.jgr.micro.respuestas.sql.models.entity.Respuesta;


/**
 * The Interface RespuestaService.
 */
public interface RespuestaService {

	/**
	 * Save all.
	 *
	 * @param respuestas the respuestas
	 * @return the iterable
	 */
	public Iterable<Respuesta> saveAll(Iterable<Respuesta> respuestas);
	
	/**
	 * Find respuesta by alumno by examen.
	 *
	 * @param alumnoId the alumno id
	 * @param examenId the examen id
	 * @return the iterable
	 */
	public Iterable<Respuesta> findRespuestaByAlumnoByExamen(Long alumnoId, Long examenId);
	
	/**
	 * Find examenes ids con respuestas by alumno.
	 *
	 * @param alumnoId the alumno id
	 * @return the iterable
	 */
	public Iterable<Long> findExamenesIdsConRespuestasByAlumno(Long alumnoId);
}
