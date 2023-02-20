package com.formacionbdi.microservicios.app.examenes.models.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.jgr.commons.modelo.asig.exam.preg.Examen;

public interface ExamenRepository extends PagingAndSortingRepository<Examen, Long>{

	@Query("select e from Examen e where e.nombre like %?1%")
	public List<Examen> findByNombre(String term);
}