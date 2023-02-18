package com.jgr.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jgr.entity.Rol;

@Repository
public interface RolRepository extends JpaRepository<Rol, Long>{

	public Optional<Rol> findById(Long id);
	
}
