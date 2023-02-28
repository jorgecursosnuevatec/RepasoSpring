package com.jgr.micro.cursos.sql.services;

import com.jgr.commons.service.CommonService;
import com.jgr.micro.cursos.sql.models.entity.Curso;


/**
 * The Interface CursoService.
 */
public interface CursoService extends CommonService<Curso> {

	/**
	 * Find curso by alumno id.
	 *
	 * @param id the id
	 * @return the curso
	 */
	public Curso findCursoByAlumnoId(Long id);
	
	/**
	 * Obtener examenes ids con respuestas alumno.
	 *
	 * @param alumnoId the alumno id
	 * @return the iterable
	 * se comunica con el microservicio respuestas para obtener los datos 
	 */
	public Iterable<Long> obtenerExamenesIdsConRespuestasAlumno(Long alumnoId);
	
	

}
