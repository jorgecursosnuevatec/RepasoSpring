package com.aepi.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aepi.model.Viajero;

@Repository
public interface IViajeroRepo extends JpaRepository<Viajero, Integer> {
	

}
