package com.aepi.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aepi.model.Matricula;

@Repository
public interface IMatriculaRepo extends JpaRepository<Matricula, Integer> {
	

}
