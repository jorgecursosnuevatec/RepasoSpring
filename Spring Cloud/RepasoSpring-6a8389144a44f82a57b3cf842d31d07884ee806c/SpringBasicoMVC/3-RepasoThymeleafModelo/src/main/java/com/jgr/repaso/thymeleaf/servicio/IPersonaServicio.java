package com.jgr.repaso.thymeleaf.servicio;

import java.util.List;
import java.util.Optional;

import com.jgr.repaso.thymeleaf.model.Persona;


/**
 * The Interface IPersonaServicio.
 */
public interface IPersonaServicio {
	
	/**
	 * Listar personas.
	 *
	 * @return the list
	 */
	public List<Persona> listarPersonas();
	
	/**
	 * Borrar persona.
	 *
	 * @param p the p
	 * @return true, if successful
	 */
	public void borrarPersona( Persona p);
	
	/**
	 * Alta persona.
	 *
	 * @param p the p
	 * @return the persona
	 */
	public Persona altaPersona(Persona p);
	
	/**
	 * Busca persona.
	 * Por probar,un optional
	 *
	 * @param p the p
	 * @return the persona
	 */
	public Optional<Persona> buscaPersona(Persona p);
	
	

}
