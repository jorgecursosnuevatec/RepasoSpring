package com.jgr.commons.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

// TODO: Auto-generated Javadoc
/**
 * The Interface CommonService.
 * Usamos el api generic
 * @param <E> the element type
 */
public interface CommonService<E> {
	
	/**
	 * Find all.
	 *
	 * @return the iterable
	 */
	public Iterable<E> findAll();
	
	/**
	 * Find all.
	 *
	 * @param pageable the pageable
	 * @return the page
	 */
	public Page<E> findAll(Pageable pageable);
	
	/**
	 * Find by id.
	 *
	 * @param id the id
	 * @return the optional
	 */
	public Optional<E> findById(Long id);
	
	/**
	 * Save.
	 *
	 * @param entity the entity
	 * @return the e
	 */
	public E save(E entity);
	
	/**
	 * Delete by id.
	 *
	 * @param id the id
	 */
	public void deleteById(Long id);

}
