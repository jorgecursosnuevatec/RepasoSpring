package com.aepi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aepi.entities.Alumno;

public interface AlumnoRepository extends JpaRepository<Alumno, Integer> {

}
