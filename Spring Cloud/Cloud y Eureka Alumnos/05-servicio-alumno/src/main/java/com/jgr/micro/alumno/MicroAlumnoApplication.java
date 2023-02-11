package com.jgr.micro.alumno;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// TODO: Auto-generated Javadoc
/**
 * The Class MicroAlumnoApplication.
 * 
 * para conectar h2
 *#la url es http://localhost:8080/h2-console/
 *##la jdbc url es jdbc:h2:mem:alumnos
 * 
 * 
 */
@SpringBootApplication
public class MicroAlumnoApplication {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(MicroAlumnoApplication.class, args);
	}

}
