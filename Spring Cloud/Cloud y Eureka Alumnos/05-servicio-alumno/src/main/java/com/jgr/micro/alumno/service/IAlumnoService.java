package com.jgr.micro.alumno.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Sort;
import org.springframework.stereotype.Service;

import com.jgr.micro.alumno.entity.Alumno;
import com.jgr.micro.alumno.repository.IAlumnoRepository;

// TODO: Auto-generated Javadoc
/**
 * The Interface IAlumnoService.
 */

public interface IAlumnoService {

	/**
	 * Find all.
	 *
	 * @return the iterable
	 */
	public Iterable<Alumno> findAll();

	/**
	 * Find by nombre like.
	 *
	 * @param nombreAlumno the nombre alumno
	 * @return the iterable
	 */
	public Iterable<Alumno> findByNombreContainsIgnoreCase(String nombreAlumno);

	/**
	 * Find by id.
	 *
	 * @param idAlumno the id alumno
	 * @return the optional
	 */
	public Optional<Alumno> findById(Long idAlumno);

	/**
	 * Save.
	 *
	 * @param alumno the alumno
	 * @return the alumno
	 */
	public Alumno save(Alumno alumno);

	/**
	 * Delete by id.
	 *
	 * @param idAlumno the id alumno
	 */
	public void deleteById(Long idAlumno);

	public Iterable<Alumno> buscaNombreOApellido(String term);

	public Iterable<Alumno> findByNombreContainingIgnoreCaseOrApellidosContainingIgnoreCase(String nombre,
			String apellido);

	public Iterable<Alumno> findAllById(Iterable<Long> ids);

	public void eliminarCursoAlumnoPorId(Long id);

}
