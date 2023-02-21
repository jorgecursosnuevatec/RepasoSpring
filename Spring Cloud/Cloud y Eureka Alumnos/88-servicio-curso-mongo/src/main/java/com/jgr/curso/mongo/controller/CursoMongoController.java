package com.jgr.curso.mongo.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jgr.curso.mongo.model.Curso;
import com.jgr.curso.mongo.service.ICursoServiceMongo;



@RestController
public class CursoMongoController {
	
	@Autowired
	private ICursoServiceMongo cursoService;
	
	
	/**
	 * Inicializar.
	 */
	@PostConstruct
	public void inicializar() {
		int limite = 4;

		Curso c;
		for (int i = 0; i < limite; i++) {
			c = new Curso();
			c.setNombre("NombreCurso"+i);
			c.setCreateAt(new Date());			
			cursoService.guardar(c);
			
		}

	}
	

	@GetMapping
	public ResponseEntity<?> listarTodos() {
		
		System.out.println("En listar todos MONGO");
		

		List<Curso> al = (List<Curso>) cursoService.listaTodos();
		
		

		if (al.size() > 0) {
			return ResponseEntity.ok(al);
		}

		return ResponseEntity.notFound().build();

	}
	

	
	
	

}
