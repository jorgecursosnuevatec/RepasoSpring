package com.jgr.micro.alumno.service;

import com.jgr.modelo.generico.alumno.Alumno;
import com.jgr.servicio.generico.service.IServiceGenerico;


/**
 * The Interface IAlumnoService.
 * Hereda de la interfaz GENERICA que he creado en 88-generico-jpa, para usarla tengo que pasarle la clase que vamos
 * a usar
 */

public interface IAlumnoService extends IServiceGenerico<Alumno>{

		
	public Iterable<Alumno> findByNombreContainsIgnoreCase(String nombreAlumno);


	public Iterable<Alumno> buscaNombreOApellido(String term);

	public Iterable<Alumno> findByNombreContainingIgnoreCaseOrApellidosContainingIgnoreCase(String nombre,
			String apellido);

	public Iterable<Alumno> findAllById(Iterable<Long> ids);

	public void eliminarCursoAlumnoPorId(Long id);

}