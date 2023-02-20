package com.formacionbdi.microservicios.app.examenes.services;

import java.util.List;

import com.jgr.commons.modelo.asig.exam.preg.Asignatura;
import com.jgr.commons.modelo.asig.exam.preg.Examen;
import com.jgr.commons.service.CommonService;

public interface ExamenService extends CommonService<Examen>{
	public List<Examen> findByNombre(String term);
	
	public Iterable<Asignatura> findAllAsignaturas();
}
