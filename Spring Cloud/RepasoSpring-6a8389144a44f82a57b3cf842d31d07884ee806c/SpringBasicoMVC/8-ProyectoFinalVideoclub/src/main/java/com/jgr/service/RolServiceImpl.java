package com.jgr.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jgr.entity.Rol;
import com.jgr.repository.RolRepository;

@Service
public class RolServiceImpl {

	@Autowired
	private RolRepository repo;
	
	public List<Rol> findAll()
	{
		return repo.findAll();
	}
	
	public Optional<Rol> findById(Long id)
	{
		return repo.findById(id);
	}
}
