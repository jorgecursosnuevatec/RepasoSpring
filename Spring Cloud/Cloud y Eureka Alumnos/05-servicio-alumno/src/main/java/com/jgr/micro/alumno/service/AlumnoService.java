package com.jgr.micro.alumno.service;

import java.util.Optional;

import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.data.domain.Sort.Direction;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Sort;

import com.jgr.micro.alumno.entity.Alumno;
import com.jgr.micro.alumno.repository.IAlumnoRepository;


/**
 * The Class AlumnoService.
 */
@Service
public class AlumnoService implements IAlumnoService {

	/** The i alumno repository. */
	@Autowired
	private IAlumnoRepository iAlumnoRepository;

	/**
	 * Find all.
	 *
	 * @return the iterable
	 */
	@Override
	@Transactional(readOnly = true)
	public Iterable<Alumno> findAll() {

		return iAlumnoRepository.findAll();
	}


	
	/**
	 * Find by id.
	 *
	 * @param idAlumno the id alumno
	 * @return the optional
	 */
	@Override
	@Transactional(readOnly = true)
	public Optional<Alumno> findById(Long idAlumno) {

		return iAlumnoRepository.findById(idAlumno);

	}

	/**
	 * Save.
	 *
	 * @param alumno the alumno
	 * @return the alumno
	 */
	@Override
	@Transactional
	public Alumno save(Alumno alumno) {
		return iAlumnoRepository.save(alumno);
	}

	/**
	 * Delete by id.
	 *
	 * @param idAlumno the id alumno
	 */
	@Override
	@Transactional
	public void deleteById(Long idAlumno) {
		iAlumnoRepository.deleteById(idAlumno);

	}

	/**
	 * Find by nombre like ignore case.
	 *
	 * @param nombreAlumno the nombre alumno
	 * @return the iterable
	 */
	@Override
	@Transactional
	public Iterable<Alumno> findByNombreLikeIgnoreCase(String nombreAlumno) {
		return iAlumnoRepository.findByNombreLikeIgnoreCase(nombreAlumno);
	}

	
}
