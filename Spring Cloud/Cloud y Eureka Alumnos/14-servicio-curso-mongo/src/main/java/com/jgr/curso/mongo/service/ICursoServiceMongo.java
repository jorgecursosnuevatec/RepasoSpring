package com.jgr.curso.mongo.service;

import java.util.List;
import java.util.Optional;

import com.jgr.curso.mongo.model.Curso;

public interface ICursoServiceMongo {
	
	public List<Curso> listaTodos();
	
	public Optional<Curso> findById(Long cursoId);
	
	public void guardar(Curso c);

}
