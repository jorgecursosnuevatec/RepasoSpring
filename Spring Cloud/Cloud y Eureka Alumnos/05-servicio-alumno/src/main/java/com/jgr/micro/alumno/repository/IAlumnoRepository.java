package com.jgr.micro.alumno.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.jgr.micro.alumno.entity.Alumno;



// TODO: Auto-generated Javadoc
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
	Iterable<Alumno> findByNombreContainsIgnoreCase(String nombreAlumno);
	

	
	/**
	 * Busca nombre O apellido.
	 *
	 * @param term the term
	 * @return the iterable
	 */
	@Query("SELECT a FROM Alumno a WHERE a.nombre LIKE %?1% OR a.apellidos LIKE %?1%")
	public Iterable<Alumno> buscaNombreOApellido(String term);
	
	/**
	 * Find by name or apellidos ignorando mayusculas/minusculas.
	 *
	 * @param nombre the nombre
	 * @param apellido the apellido
	 * @return the iterable
	 */
	public Iterable<Alumno> findByNombreContainingIgnoreCaseOrApellidosContainingIgnoreCase(String nombre,String apellido);

}
