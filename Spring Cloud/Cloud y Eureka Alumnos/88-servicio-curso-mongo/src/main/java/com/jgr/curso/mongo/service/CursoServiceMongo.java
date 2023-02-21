package com.jgr.curso.mongo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jgr.curso.mongo.model.Curso;
import com.jgr.curso.mongo.repository.ICursoRepositoryMongo;

@Service
public class CursoServiceMongo implements  ICursoServiceMongo{
	
	@Autowired
	private ICursoRepositoryMongo cursoRepository;

	@Override
	public List<Curso> listaTodos() {
		return cursoRepository.findAll();
	}

	@Override
	public Optional<Curso> findById(Long cursoId) {
		
		return cursoRepository.findById(cursoId.toString());
		
		

	}

	
	@Override
	public void guardar(Curso c) {
		cursoRepository.save(c);
		
	}

}
