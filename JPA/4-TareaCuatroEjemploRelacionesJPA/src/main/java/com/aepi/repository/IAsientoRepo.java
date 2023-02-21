package com.aepi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aepi.model.Asiento;

@Repository
public interface IAsientoRepo extends JpaRepository<Asiento, Integer> {
	
}
