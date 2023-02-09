package com.jgr.micro.alumno.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.jgr.micro.alumno.entity.Alumno;



/**
 * The Interface IAlumnoRepository.
 * extendemos de pagingandsorting
 */
public interface IAlumnoRepository extends JpaRepository<Alumno, Long>{

	/**
	 * Find by nombre like ignore case.
	 *
	 * @param nombreAlumno the nombre alumno
	 * @return the iterable
	 */
	Iterable<Alumno> findByNombreLikeIgnoreCase(String nombreAlumno);
	
	/**
	 * Find all order by by nombre.
	 *
	 * @return the iterable
	 */
	//Iterable<Alumno> findAllOrderByNombre(Sort sort);

}
