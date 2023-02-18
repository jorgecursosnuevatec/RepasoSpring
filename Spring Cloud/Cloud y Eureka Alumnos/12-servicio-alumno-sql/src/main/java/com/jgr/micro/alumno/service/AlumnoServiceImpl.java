package com.jgr.micro.alumno.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jgr.micro.alumno.entity.Alumno;
import com.jgr.micro.alumno.repository.IAlumnoRepository;
import com.jgr.servicio.generico.service.ServiceGenericoImpl;

import lombok.extern.slf4j.Slf4j;

// TODO: Auto-generated Javadoc
/**
 * The Class AlumnoService.
 * la clase tiene que heredar de ServiceGenericoImpl que es la que tiene los metodos
 * declarados que hemos heredado de IServiceGenerico en IAlumnoService
 * como parametros le tenemos que pasar la clase que vamos a usar en esta capa de servicio
 * que es Alumno y su repositorio, que es IAlumnoRepository
 */
@Service

/** The Constant log. */

/** The Constant log. */
@Slf4j // log lombok
public class AlumnoServiceImpl extends ServiceGenericoImpl<Alumno,IAlumnoRepository> 
implements IAlumnoService {

	/** The i alumno repository. */
	@Autowired
	private IAlumnoRepository iAlumnoRepository;

	
	/**
	 * Find by nombre contains ignore case.
	 *
	 * @param nombreAlumno the nombre alumno
	 * @return the iterable
	 */
	@Override
	@Transactional(readOnly=true)
	public Iterable<Alumno> findByNombreContainsIgnoreCase(String nombreAlumno) {
		log.debug("en findNombreLikeIgnoreCase" + nombreAlumno);
		return iAlumnoRepository.findByNombreContainsIgnoreCase(nombreAlumno);
	}

	/**
	 * Busca nombre O apellido.
	 *
	 * @param term the term
	 * @return the iterable
	 */
	@Override
	@Transactional(readOnly=true)
	public Iterable<Alumno> buscaNombreOApellido(String term) {
		
		return iAlumnoRepository.buscaNombreOApellido(term);
	}
	
	/**
	 * Find by nombre containing ignore case or apellidos containing ignore case.
	 *
	 * @param nombre the nombre
	 * @param apellido the apellido
	 * @return the iterable
	 */
	@Override
	@Transactional(readOnly=true)
	public Iterable<Alumno> findByNombreContainingIgnoreCaseOrApellidosContainingIgnoreCase(String nombre,
			String apellido) {
		return iAlumnoRepository.findByNombreContainingIgnoreCaseOrApellidosContainingIgnoreCase(nombre,apellido);
		
	}

	/**
	 * Find all by id.
	 *
	 * @param ids the ids
	 * @return the iterable
	 */
	@Override
	@Transactional(readOnly=true)
	public Iterable<Alumno> findAllById(Iterable<Long> ids) {
		return iAlumnoRepository.findAllById(ids);
		
	}

	/**
	 * Eliminar curso alumno por id.
	 *
	 * @param id the id
	 */
	@Override
	@Transactional
	public void eliminarCursoAlumnoPorId(Long id) {
		// TODO Auto-generated method stub
		
	}

}