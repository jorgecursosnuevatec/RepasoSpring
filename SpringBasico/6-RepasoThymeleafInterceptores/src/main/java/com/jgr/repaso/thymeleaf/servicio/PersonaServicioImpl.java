package com.jgr.repaso.thymeleaf.servicio;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jgr.repaso.thymeleaf.interceptores.TiempoTranscurridoInterceptor;
import com.jgr.repaso.thymeleaf.model.Persona;
import com.jgr.repaso.thymeleaf.repository.IPersonaRepositorio;





/**
 * The Class PersonaServicioImpl.
 */
@Service
public class PersonaServicioImpl implements IPersonaServicio{
	
	private static final Logger logger = LoggerFactory.getLogger(PersonaServicioImpl.class);
	
	/** The i persona repositorio. */
	@Autowired
	private IPersonaRepositorio iPersonaRepositorio;

	/**
	 * Listar personas.
	 *
	 * @return the list
	 */
	@Override
	//del paquete de spring
	@Transactional(readOnly=true)
	public List<Persona> listarPersonas() {
		return iPersonaRepositorio
				.findAll()
				.stream()
				.sorted((a,b)->(b.getIdPersona().compareTo(a.getIdPersona())))
				.collect(Collectors.toList())
				;
	}

	/**
	 * Borrar persona.
	 *
	 * @param p the p
	 */
	@Override
	@Transactional
	public void borrarPersona(Persona p) {
		
		 iPersonaRepositorio.delete(p);
	}

	/**
	 * Alta persona.
	 *
	 * @param p the p
	 * @return the persona
	 */
	@Override
	@Transactional
	public Persona altaPersona(Persona p) {
		
		return iPersonaRepositorio.save(p);
	}

	/**
	 * Busca persona.
	 *
	 * @param p the p
	 * @return the persona
	 */
	@Override
	@Transactional(readOnly=true)
	public Optional<Persona> buscaPersona(Persona p) {
		return iPersonaRepositorio
				.findById(p.getIdPersona())
				.or(()->Optional.empty())
				;
		
	}
}
