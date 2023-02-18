package com.jgr.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jgr.entity.Pelicula;




@Repository
public interface PeliculaRepository extends JpaRepository<Pelicula, Long> {

	public Optional<Pelicula> findByTituloPeliculaIgnoreCase(String titulo);
	public Optional<Pelicula> findByFechaEstrenoPelicula(Date fecha);
	public List<Pelicula> findByTituloPeliculaContaining(String titulo);	
	public void deletePeliculaById(int id);

}
