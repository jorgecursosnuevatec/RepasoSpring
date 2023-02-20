package com.jgr.servicio.curso.sql.service;


import com.jgr.servicio.curso.sql.entity.Curso;
import com.jgr.servicio.generico.service.IServiceGenerico;

/**
 * The Interface ICursoService.
 * 
 * HEREDAMOS DE ISERVICEGENERICO
 * 
 * 
 */
public interface ICursoService extends IServiceGenerico<Curso>{
	
	
	public Curso findCursoByAlumnoId(Long id);
	
	public Iterable<Long> obtenerExamenesIdsConRespuestasAlumno(Long alumnoId);
	

}
