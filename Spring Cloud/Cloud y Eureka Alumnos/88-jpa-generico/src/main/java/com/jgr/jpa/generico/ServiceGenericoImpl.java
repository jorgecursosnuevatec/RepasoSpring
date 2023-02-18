/*
 * 
 */
package com.jgr.jpa.generico;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;

/**
 * The Class AlumnoService. implementa la interfaz GENERICA como parametros le
 * pasamos la capa de repositorio,tambien GENERICA 
 * E->entidad/clase que le vamos
 * a pasar cuando lo implementemos 
 * R->nombre que le damos al repositorio que se va a usar con jpa  
 * Long->clave/indice de la entidad/clase
 * Lo vamos a HEREDAR, por eso le quitamos el service
 * Hay que meterlo en el POM de los que lo van a usar
 * @param <E> the element type
 * @param <R> the generic type
 * 
 * 
 */
//@Service 
@Slf4j // log lombok
public class ServiceGenericoImpl<E,R extends JpaRepository<E,Long>> implements IServiceGenerico<E> {

	
	/** The repositorio. 
	 El repositorio es generico,vale para todos los objetos. Se lo pasaremos como 
	 parametro cuando lo instanciemos. PROTECTED es porque lo vamos a heredar */
	@Autowired
	protected R repositorio;

	
	/**
	 * Find all.
	 *
	 * @return the iterable
	 */
	@Override
	@Transactional(readOnly = true)
	public Iterable<E> findAll() {

		return repositorio.findAll();
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

		return repositorio.findById(id);

	}

	
	/**
	 * Save.
	 *
	 * @param objeto the objeto
	 * @return the e
	 */
	@Override
	@Transactional
	public E save(E objeto) {
		return repositorio.save(objeto);
	}

	
	/**
	 * Delete by id.
	 *
	 * @param id the id
	 */
	@Override
	@Transactional
	public void deleteById(Long id) {
		repositorio.deleteById(id);

	}

	
}
