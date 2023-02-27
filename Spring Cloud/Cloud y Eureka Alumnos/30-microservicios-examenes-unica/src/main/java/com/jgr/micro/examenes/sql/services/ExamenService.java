package com.jgr.micro.examenes.sql.services;

import java.util.List;

import com.jgr.commons.modelo.asig.exam.preg.Asignatura;
import com.jgr.commons.modelo.asig.exam.preg.Examen;
import com.jgr.commons.service.CommonService;


/**
 * The Interface ExamenService.
 */
public interface ExamenService extends CommonService<Examen>{
	
	/**
	 * Find by nombre.
	 *
	 * @param term the term
	 * @return the list
	 */
	public List<Examen> findByNombre(String term);
	
	/**
	 * Find all asignaturas.
	 *
	 * @return the iterable
	 */
	public Iterable<Asignatura> findAllAsignaturas();
}
