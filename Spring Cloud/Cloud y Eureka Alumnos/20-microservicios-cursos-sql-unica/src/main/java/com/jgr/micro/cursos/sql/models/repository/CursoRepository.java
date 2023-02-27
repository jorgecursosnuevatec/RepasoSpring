package com.jgr.micro.cursos.sql.models.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.jgr.micro.cursos.sql.models.entity.Curso;

// TODO: Auto-generated Javadoc
/**
 * The Interface CursoRepository.
 * heredeamos de pagingandsorting  
 */
public interface CursoRepository extends PagingAndSortingRepository<Curso, Long>{

	
	/**
	 * Find curso by alumno id.
	 *
	 * @param id the id
	 * @return the curso
	 */
	//esta todo dentro de la clase curso
	@Query("select c from Curso c join fetch c.alumnos a where a.id=?1")
	public Curso findCursoByAlumnoId(Long id);
	

	
	
}
