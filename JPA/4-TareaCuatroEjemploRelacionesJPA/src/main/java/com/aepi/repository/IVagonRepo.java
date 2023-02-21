package com.aepi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aepi.model.Vagon;

@Repository
public interface IVagonRepo extends JpaRepository<Vagon, Integer> {
	
}
