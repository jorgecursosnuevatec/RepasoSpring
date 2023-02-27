package com.jgr.micro.alumnos.sql.models.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.jgr.commons.modelo.alumnos.Alumno;


// TODO: Auto-generated Javadoc
/**
 * The Interface AlumnoRepository.
 * heredeamos de pagingandsorting 
 */
public interface AlumnoRepository extends PagingAndSortingRepository<Alumno, Long> {

	/**
	 * Find by nombre or apellido ignoring case.
	 *
	 * @param term the term
	 * @return the list
	 */
	@Query("select a from Alumno a where a.nombre like %?1% or a.apellido like %?1%")
	public List<Alumno> findByNombreOrApellidoIgnoringCase(String term);
	
	
	
	
	
}
