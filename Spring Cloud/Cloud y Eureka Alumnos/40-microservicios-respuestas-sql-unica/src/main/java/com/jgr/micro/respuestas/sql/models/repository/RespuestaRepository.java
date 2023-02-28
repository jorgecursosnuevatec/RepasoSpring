package com.jgr.micro.respuestas.sql.models.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.jgr.micro.respuestas.sql.models.entity.Respuesta;


/**
 * The Interface RespuestaRepository.
 */
public interface RespuestaRepository extends CrudRepository<Respuesta, Long> {

	/**
	 * Find respuesta by alumno by examen.
	 *
	 * @param alumnoId the alumno id
	 * @param examenId the examen id
	 * @return the iterable
	 * 
	 * a partir de la respuesta ya tenemos la relacion con el alumno y tambien con la pregunta
	 * con la pregunta ya podemos sacar el examen
	 */
	@Query("select r from Respuesta r join fetch r.alumno a join fetch r.pregunta p join fetch p.examen e where a.id=?1 and e.id=?2")
	public Iterable<Respuesta> findRespuestaByAlumnoByExamen(Long alumnoId, Long examenId);
	
	/**
	 * Find examenes ids con respuestas by alumno.
	 *
	 * @param alumnoId the alumno id
	 * @return the iterable
	 * a partir de la respuesta obtenemos el alumno y la pregunta. con la pregunta sacamos el examen
	 */
	@Query("select e.id from Respuesta r join r.alumno a join r.pregunta p join p.examen e where a.id=?1 group by e.id")
	public Iterable<Long> findExamenesIdsConRespuestasByAlumno(Long alumnoId);
}
