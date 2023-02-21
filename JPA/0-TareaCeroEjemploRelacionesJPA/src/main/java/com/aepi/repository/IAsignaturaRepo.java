package com.aepi.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aepi.model.Asignatura;

@Repository
public interface IAsignaturaRepo extends JpaRepository<Asignatura, Integer> {
	

}
