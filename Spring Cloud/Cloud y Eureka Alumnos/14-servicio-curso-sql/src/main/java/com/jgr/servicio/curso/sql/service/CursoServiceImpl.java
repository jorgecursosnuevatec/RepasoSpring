package com.jgr.servicio.curso.sql.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jgr.servicio.curso.sql.controller.CursoController;
import com.jgr.servicio.curso.sql.entity.Curso;
import com.jgr.servicio.curso.sql.repository.ICursoRepository;
import com.jgr.servicio.generico.service.ServiceGenericoImpl;

import lombok.extern.slf4j.Slf4j;


/**
 * The Class CursoServiceImpl.
 * 
 * EL REPOSITORIO NO LO INYECTAMOS AQUI PORQUE LO HEREDAMOS DE ServiceGenericoImpl
 * 
 */
@Service
@Slf4j
public class CursoServiceImpl  extends ServiceGenericoImpl<Curso,ICursoRepository> 
implements ICursoService{
	
	
	private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(CursoServiceImpl.class);
	

	@Override
	public Iterable<Curso> findAll() {
		return repositorio.findAll();
	}

	/**
	 * Find by id.
	 *
	 * @param id the id
	 * @return the optional
	 */
	@Override
	public Optional<Curso> findById(Long id) {
		
		return repositorio.findById(id);
		
	}

	/**
	 * Save.
	 *
	 * @param entidad the entidad
	 * @return the curso
	 */
	@Override
	public Curso save(Curso entidad) {
		return repositorio.save(entidad);
	}

	/**
	 * Delete by id.
	 *
	 * @param id the id
	 */
	@Override
	public void deleteById(Long id) {
		repositorio.deleteById(id);
		
	}

}
