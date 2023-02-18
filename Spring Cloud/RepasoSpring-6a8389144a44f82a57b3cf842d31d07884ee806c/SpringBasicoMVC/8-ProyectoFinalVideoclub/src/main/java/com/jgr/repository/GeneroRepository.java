package com.jgr.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jgr.entity.Genero;



@Repository
public interface GeneroRepository extends JpaRepository<Genero, Long> {

	Optional<Genero> findByTipoGenero(String tipo);

	Optional<Genero> findFirstByTipoGenero(String texto);

}
