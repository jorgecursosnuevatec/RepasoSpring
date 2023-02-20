package com.jgr.servicio.curso.sql.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jgr.servicio.curso.sql.entity.Curso;



/**
 * The Interface ICursoRepository.
 * extendemos de pagingandsorting
 */
public interface ICursoRepository extends JpaRepository<Curso, Long>{
	

	@Query("select c from Curso c join fetch c.alumnos a where a.id=?1")
	public Curso findCursoByAlumnoId(Long id);

	
}
