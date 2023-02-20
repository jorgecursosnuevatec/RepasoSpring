package com.jgr.commons.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

// TODO: Auto-generated Javadoc
/**
 * The Class CommonServiceImpl.
 * pasamos la capa de repositorio,tambien GENERICA 
 * E->entidad/clase que le vamos
 * a pasar cuando lo implementemos 
 * R->nombre que le damos al repositorio que se va a usar con jpa  
 * Long->clave/indice de la entidad/clase
 * Lo vamos a HEREDAR, por eso le quitamos el service
 * Hay que meterlo en el POM de los que lo van a usar
 * @param <E> the element type
 * @param <R> the generic type
 */
public class CommonServiceImpl<E, R extends PagingAndSortingRepository<E, Long>> implements CommonService<E> {

	/** The repository. 
 	El repositorio es generico,vale para todos los objetos. Se lo pasaremos como 
	 parametro cuando lo instanciemos. PROTECTED es porque lo vamos a heredar */
	@Autowired
	protected R repository;
	
	/**
	 * Find all.
	 *
	 * @return the iterable
	 */
	@Override
	@Transactional(readOnly = true)
	public Iterable<E> findAll() {
		return repository.findAll();
	}

	/**
	 * Find by id.
	 *
	 * @param id the id
	 * @return the optional
	 */
	@Override
	@Transactional(readOnly = true)
	public Optional<E> findById(Long id) {
		return repository.findById(id);
	}

	/**
	 * Save.
	 *
	 * @param entity the entity
	 * @return the e
	 */
	@Override
	@Transactional
	public E save(E entity) {
		return repository.save(entity);
	}

	/**
	 * Delete by id.
	 *
	 * @param id the id
	 */
	@Override
	@Transactional
	public void deleteById(Long id) {
		repository.deleteById(id);
	}

	/**
	 * Find all.
	 *
	 * @param pageable the pageable
	 * @return the page
	 */
	@Override
	@Transactional(readOnly = true)
	public Page<E> findAll(Pageable pageable) {
		return repository.findAll(pageable);
	}

}
