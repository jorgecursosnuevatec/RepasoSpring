package com.jgr.micro.cursos.sql.services;

import com.jgr.commons.service.CommonService;
import com.jgr.micro.cursos.sql.models.entity.Curso;

public interface CursoService extends CommonService<Curso> {

	public Curso findCursoByAlumnoId(Long id);
	
	public Iterable<Long> obtenerExamenesIdsConRespuestasAlumno(Long alumnoId);
	
	

}
