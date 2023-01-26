package com.jgr.repaso.thymeleaf.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.jgr.repaso.thymeleaf.model.Persona;

/**
 * The Interface IPersonaRepositorio.
 * voy a probar con Crud y Jpa, a ver cual esta mejor
 * 
 * 
 */

public interface IPersonaRepositorio extends
CrudRepository<Persona, Long>,
PagingAndSortingRepository<Persona, Long>,
JpaRepository<Persona, Long>
 {

}
