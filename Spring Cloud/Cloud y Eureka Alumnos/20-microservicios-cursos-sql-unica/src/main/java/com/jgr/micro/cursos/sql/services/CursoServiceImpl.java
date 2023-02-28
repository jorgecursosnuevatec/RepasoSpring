package com.jgr.micro.cursos.sql.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jgr.commons.service.CommonServiceImpl;
import com.jgr.micro.cursos.sql.clients.RespuestaFeignClient;
import com.jgr.micro.cursos.sql.models.entity.Curso;
import com.jgr.micro.cursos.sql.models.repository.CursoRepository;


/**
 * The Class CursoServiceImpl.
 */
@Service
public class CursoServiceImpl extends CommonServiceImpl<Curso, CursoRepository> implements CursoService {

	/** The client. */
	@Autowired
	private RespuestaFeignClient client;
	
	/**
	 * Find curso by alumno id.
	 *
	 * @param id the id
	 * @return the curso
	 */
	@Override
	@Transactional(readOnly = true)
	public Curso findCursoByAlumnoId(Long id) {
		return repository.findCursoByAlumnoId(id);
	}

	/**
	 * Obtener examenes ids con respuestas alumno.
	 *
	 * @param alumnoId the alumno id
	 * @return the iterable
	 * se comunica con el microservicio respuestas para obtener los datos
	 */
	@Override
	@Transactional(readOnly = true)
	public Iterable<Long> obtenerExamenesIdsConRespuestasAlumno(Long alumnoId) {
		return client.obtenerExamenesIdsConRespuestasAlumno(alumnoId);
	}

	

	
	
}
