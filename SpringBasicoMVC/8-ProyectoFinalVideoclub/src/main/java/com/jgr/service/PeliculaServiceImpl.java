package com.jgr.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.jgr.entity.Pelicula;
import com.jgr.repository.PeliculaRepository;




@Service
public class PeliculaServiceImpl implements IPeliculaService{
	
	@Autowired
	private PeliculaRepository peliRepo;

	@Override
	public List<Pelicula> findAllPeliculas() {
		
		return peliRepo.findAll();
	}

	@Override
	public Pelicula save(Pelicula peli) {
		
		peli.setTituloPelicula(peli.getTituloPelicula().toUpperCase());
		
		return peliRepo.save(peli);

	}

	@Override
	public Optional<Pelicula> findById(int id) {
		return peliRepo.findById(Long.valueOf(id));
		 
	}

	@Override
	public Optional<Pelicula> findByTituloPeliculaIgnoreCase(String titulo) {
		
		return peliRepo.findByTituloPeliculaIgnoreCase(titulo);
	}

	@Override
	public Optional<Pelicula> findByFechaEstrenoPelicula(Date fecha) {
		return peliRepo.findByFechaEstrenoPelicula(fecha);
	}

	@Override
	public List<Pelicula> findByTituloPeliculaContaining(String titulo) {
		
		return peliRepo.findByTituloPeliculaContaining(titulo.toUpperCase());
	}

	@Override
	public Page<Pelicula> findAllPeliculas(Pageable pageable) {
		return peliRepo.findAll(pageable);
	}

	@Override
	public void deletePeliculaById(int id) {
		peliRepo.deleteById(Long.valueOf(id));
		
		
	}

	@Override
	public Optional<Pelicula> findById(Long id) {
		return peliRepo.findById(id);
		
	}


}
