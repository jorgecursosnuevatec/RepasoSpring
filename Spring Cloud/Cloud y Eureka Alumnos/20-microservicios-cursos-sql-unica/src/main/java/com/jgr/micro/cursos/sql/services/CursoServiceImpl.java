package com.jgr.micro.cursos.sql.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jgr.commons.service.CommonServiceImpl;
import com.jgr.micro.cursos.sql.clients.RespuestaFeignClient;
import com.jgr.micro.cursos.sql.models.entity.Curso;
import com.jgr.micro.cursos.sql.models.repository.CursoRepository;

@Service
public class CursoServiceImpl extends CommonServiceImpl<Curso, CursoRepository> implements CursoService {

	@Autowired
	private RespuestaFeignClient client;
	
	@Override
	@Transactional(readOnly = true)
	public Curso findCursoByAlumnoId(Long id) {
		return repository.findCursoByAlumnoId(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Iterable<Long> obtenerExamenesIdsConRespuestasAlumno(Long alumnoId) {
		return client.obtenerExamenesIdsConRespuestasAlumno(alumnoId);
	}

	

	
	
}
