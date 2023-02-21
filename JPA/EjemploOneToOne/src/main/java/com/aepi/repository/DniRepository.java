package com.aepi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aepi.entity.Dni;

public interface DniRepository extends JpaRepository<Dni, Integer> {

}
