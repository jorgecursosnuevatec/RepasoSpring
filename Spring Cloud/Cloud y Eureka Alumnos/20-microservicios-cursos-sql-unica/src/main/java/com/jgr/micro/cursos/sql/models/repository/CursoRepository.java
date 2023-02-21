package com.jgr.micro.cursos.sql.models.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.jgr.commons.modelo.alumnos.Alumno;
import com.jgr.micro.cursos.sql.models.entity.Curso;

public interface CursoRepository extends PagingAndSortingRepository<Curso, Long>{

	@Query("select c from Curso c join fetch c.alumnos a where a.id=?1")
	public Curso findCursoByAlumnoId(Long id);
	

	
	
}
