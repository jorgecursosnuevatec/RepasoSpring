package com.jgr.servicio.curso.sql.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.jgr.controller.generico.ControladorGenerico;
import com.jgr.model.sql.alumno.entity.Alumno;
import com.jgr.servicio.curso.sql.entity.Curso;
import com.jgr.servicio.curso.sql.service.ICursoService;

import lombok.extern.slf4j.Slf4j;





VER COMO HAGO LA VALIDACION EN GUARDAR,NO ESTA DEFINIDO EL POST EN EL CONTROLLER GENERICO

@Slf4j
@RestController
public class CursoController  extends ControladorGenerico <Curso,ICursoService>{
	
	
	
	/** The Constant log. */
	private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(CursoController.class);

	
	@Autowired
	private ICursoService cursoService;
	
	
	
	/**
	 * Inicializar.
	 */
//	@PostConstruct
	public void inicializar() {
		int limite = 4;

		Alumno al;
		Curso cur;
		List<Alumno> alumnosLista = new ArrayList<>();

		
		for (int i = 0 ;i<limite;i++){
			
			cur = new Curso();
			cur.setNombreCurso("Curso"+i);
			log.debug("Antes del primer save");
			cursoService.save(cur);
			log.debug("DESPUES del primer save");
			
			
			for (int j =0;j<limite;j++) {
				al = new Alumno();
				al.setNombre("Nombre" + i+j);
				al.setApellidos("Apellido" + i+j);
				al.setEmail("Email" + i+j + "@mail.com");		
//				alumnosLista.add(al);
				log.debug("Antes del add");
				cur.addAlumno(al);
				log.debug("Despues del add");
				
			}
			//cur.addAlumno(alumnosLista);
			//asigno cada alumno al curso
//			alumnosLista.forEach(cur::addAlumno);
			
			log.debug("despues del bucle");
			cursoService.save(cur);
			log.debug("despues del bucle2");
		}
		
		
		

	
		
		
		

	}

	

}
