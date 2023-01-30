package com.jgr.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.jgr.entity.Pelicula;



public interface IPeliculaService {
	
	public List<Pelicula> findAllPeliculas();
	
	public Pelicula save(Pelicula peli);
	
	public Optional<Pelicula> findById(int id);
	
	public Optional<Pelicula> findByTituloPeliculaIgnoreCase(String titulo);
	
	public Optional<Pelicula> findByFechaEstrenoPelicula(Date fecha);
	
	public List<Pelicula> findByTituloPeliculaContaining(String titulo);
	
	public Page<Pelicula> findAllPeliculas(Pageable pageable);
	
	public void deletePeliculaById(int id);

	public Optional<Pelicula> findById(Long id);
	
	

}
