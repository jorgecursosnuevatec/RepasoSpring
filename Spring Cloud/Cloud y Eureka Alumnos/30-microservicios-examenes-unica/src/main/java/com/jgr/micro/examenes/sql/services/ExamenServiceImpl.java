package com.jgr.micro.examenes.sql.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jgr.commons.modelo.asig.exam.preg.Asignatura;
import com.jgr.commons.modelo.asig.exam.preg.Examen;
import com.jgr.commons.service.CommonServiceImpl;
import com.jgr.micro.examenes.sql.models.repository.AsignaturaRepository;
import com.jgr.micro.examenes.sql.models.repository.ExamenRepository;


/**
 * The Class ExamenServiceImpl.
 * El repositorio de examen los importamos del commonserviceimpl,no es necesario porque como heredamos
 * y le pasamos el examen yel examenrepository
 * el que tenemos que inyectar es el de asignatura,ya que no lo importamos
 */
@Service
public class ExamenServiceImpl extends CommonServiceImpl<Examen, ExamenRepository> implements ExamenService {

	/** The asignatura repository. */
	
	@Autowired
	private AsignaturaRepository asignaturaRepository;
	
	/**
	 * Find by nombre.
	 *
	 * @param term the term
	 * @return the list
	 */
	@Override
	@Transactional(readOnly = true)
	public List<Examen> findByNombre(String term) {
		return repository.findByNombre(term);
	}

	/**
	 * Find all asignaturas.
	 *
	 * @return the iterable
	 */
	@Override
	@Transactional(readOnly = true)
	public Iterable<Asignatura> findAllAsignaturas() {
		return asignaturaRepository.findAll();
	}

}
