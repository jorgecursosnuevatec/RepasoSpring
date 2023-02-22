package com.jgr.micro.examenes.sql.models.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.jgr.commons.modelo.asig.exam.preg.Examen;

// TODO: Auto-generated Javadoc
/**
 * The Interface ExamenRepository.
 */
public interface ExamenRepository extends PagingAndSortingRepository<Examen, Long>{

	/**
	 * Find by nombre.
	 *
	 * @param term the term
	 * @return the list
	 */
	@Query("select e from Examen e where e.nombre like %?1%")
	public List<Examen> findByNombre(String term);
}