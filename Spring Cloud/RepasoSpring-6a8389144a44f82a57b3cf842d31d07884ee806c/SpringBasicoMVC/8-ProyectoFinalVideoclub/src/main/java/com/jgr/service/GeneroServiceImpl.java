package com.jgr.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.jgr.entity.Genero;
import com.jgr.repository.GeneroRepository;




@Service
public class GeneroServiceImpl implements IGeneroService{
	
	@Autowired
	private GeneroRepository generoRepo;

	@Override
	public List<Genero> findAll() {
		return generoRepo.findAll();
	}

	@Override
	public Genero save(Genero gen) {
		
		gen.setTipoGenero(gen.getTipoGenero().toUpperCase());
		return generoRepo.save(gen);
	}

	@Override
	public Optional<Genero> findById(int id) {
		return generoRepo.findById(Long.valueOf(id));
	}

	@Override
	public Optional<Genero> findByTipo(String tipo) {
		return generoRepo.findByTipoGenero(tipo.toUpperCase()); 
		
	}

	@Override
	public Optional<Genero> findFirstByTipoGenero(String tipoGenero) {
		return generoRepo.findFirstByTipoGenero(tipoGenero.toUpperCase());
		
	}

	@Override
	public void deleteById(int id) {
		generoRepo.deleteById(Long.valueOf(id));
		
		
	}

	@Override
	public Page<Genero> findAll(Pageable pageable) {
		return generoRepo.findAll(pageable);
	}

	

}
