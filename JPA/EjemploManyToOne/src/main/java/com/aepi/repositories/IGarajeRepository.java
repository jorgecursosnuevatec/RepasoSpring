package com.aepi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aepi.entities.Garage;

public interface IGarajeRepository extends JpaRepository<Garage, Long> {
	Garage findById(long id);
}
