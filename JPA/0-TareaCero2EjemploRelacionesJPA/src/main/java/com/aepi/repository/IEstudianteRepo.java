package com.aepi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aepi.model.Estudiante;

@Repository
public interface IEstudianteRepo extends JpaRepository<Estudiante, Integer> {
	
}
