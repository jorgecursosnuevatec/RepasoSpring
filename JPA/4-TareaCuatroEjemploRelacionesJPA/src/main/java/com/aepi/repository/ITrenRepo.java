package com.aepi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aepi.model.Tren;

@Repository
public interface ITrenRepo extends JpaRepository<Tren, Integer> {
	
}
