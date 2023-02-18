package com.jgr.micro.alumno.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jgr.micro.alumno.entity.Alumno;
import com.jgr.micro.alumno.repository.IAlumnoRepository;
import com.jgr.servicio.generico.service.ServiceGenericoImpl;

import lombok.extern.slf4j.Slf4j;

/**
 * The Class AlumnoService.
 * la clase tiene que heredar de ServiceGenericoImpl que es la que tiene los metodos
 * declarados que hemos heredado de IServiceGenerico en IAlumnoService
 * como parametros le tenemos que pasar la clase que vamos a usar en esta capa de servicio
 * que es Alumno y su repositorio, que es IAlumnoRepository
 */
@Service
@Slf4j // log lombok
public class AlumnoServiceImpl extends ServiceGenericoImpl<Alumno,IAlumnoRepository> 
implements IAlumnoService {

	/** The i alumno repository. */
	@Autowired
	private IAlumnoRepository iAlumnoRepository;

	
	@Override
	@Transactional(readOnly=true)
	public Iterable<Alumno> findByNombreContainsIgnoreCase(String nombreAlumno) {
		log.debug("en findNombreLikeIgnoreCase" + nombreAlumno);
		return iAlumnoRepository.findByNombreContainsIgnoreCase(nombreAlumno);
	}

	@Override
	@Transactional(readOnly=true)
	public Iterable<Alumno> buscaNombreOApellido(String term) {
		
		return iAlumnoRepository.buscaNombreOApellido(term);
	}
	@Override
	@Transactional(readOnly=true)
	public Iterable<Alumno> findByNombreContainingIgnoreCaseOrApellidosContainingIgnoreCase(String nombre,
			String apellido) {
		return iAlumnoRepository.findByNombreContainingIgnoreCaseOrApellidosContainingIgnoreCase(nombre,apellido);
		
	}

	@Override
	@Transactional(readOnly=true)
	public Iterable<Alumno> findAllById(Iterable<Long> ids) {
		return iAlumnoRepository.findAllById(ids);
		
	}

	@Override
	@Transactional
	public void eliminarCursoAlumnoPorId(Long id) {
		// TODO Auto-generated method stub
		
	}

}