package com.jgr.micro.respuestas.sql.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jgr.micro.respuestas.sql.models.entity.Respuesta;
import com.jgr.micro.respuestas.sql.models.repository.RespuestaRepository;


/**
 * The Class RespuestaServiceImpl.
 */
@Service
public class RespuestaServiceImpl implements RespuestaService {

	/** The repository. */
	@Autowired
	private RespuestaRepository repository;
	
	/**
	 * Save all.
	 *
	 * @param respuestas the respuestas
	 * @return the iterable
	 */
	@Override
	@Transactional
	public Iterable<Respuesta> saveAll(Iterable<Respuesta> respuestas) {
		return repository.saveAll(respuestas);
	}

	/**
	 * Find respuesta by alumno by examen.
	 *
	 * @param alumnoId the alumno id
	 * @param examenId the examen id
	 * @return the iterable
	 */
	@Override
	@Transactional(readOnly = true)
	public Iterable<Respuesta> findRespuestaByAlumnoByExamen(Long alumnoId, Long examenId) {
		return repository.findRespuestaByAlumnoByExamen(alumnoId, examenId);
	}

	/**
	 * Find examenes ids con respuestas by alumno.
	 *
	 * @param alumnoId the alumno id
	 * @return the iterable
	 */
	@Override
	@Transactional(readOnly = true)
	public Iterable<Long> findExamenesIdsConRespuestasByAlumno(Long alumnoId) {
		return repository.findExamenesIdsConRespuestasByAlumno(alumnoId);
	}

}
