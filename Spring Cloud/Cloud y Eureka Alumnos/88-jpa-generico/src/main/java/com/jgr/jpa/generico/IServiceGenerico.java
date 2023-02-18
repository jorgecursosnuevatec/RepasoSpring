
package com.jgr.jpa.generico;

import java.util.Optional;


/**
 * The Interface IServiceGenerico.
 * Usamos el api generic
 *
 * @param <E> the element type
 */
public interface IServiceGenerico<E> {

	
	public Iterable<E> findAll();
	
	public Optional<E> findById(Long id);

	
	public E save(E entidad);
	
	
	public void deleteById(Long id);
}
