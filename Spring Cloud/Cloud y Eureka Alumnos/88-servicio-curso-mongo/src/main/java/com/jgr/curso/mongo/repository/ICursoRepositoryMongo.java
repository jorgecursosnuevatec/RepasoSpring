package com.jgr.curso.mongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.jgr.curso.mongo.model.Curso;

public interface ICursoRepositoryMongo  extends MongoRepository<Curso, String>{

}
