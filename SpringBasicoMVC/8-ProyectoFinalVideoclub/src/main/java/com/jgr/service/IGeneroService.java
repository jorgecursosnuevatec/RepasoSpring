package com.jgr.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.jgr.entity.Genero;



public interface IGeneroService {

	public List<Genero> findAll();
	
	public Genero save(Genero gen);
	
	public Optional<Genero> findById(int id);
	
	public Optional<Genero> findByTipo(String tipo);

	public Optional <Genero> findFirstByTipoGenero(String tipoGenero);
	
	public void deleteById(int id);
	
	public Page<Genero> findAll(Pageable pageable);
	

}
