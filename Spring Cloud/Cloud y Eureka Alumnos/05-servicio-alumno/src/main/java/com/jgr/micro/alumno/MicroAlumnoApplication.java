package com.jgr.micro.alumno;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// TODO: Auto-generated Javadoc
/**
 * The Class MicroAlumnoApplication.
 * 
 *H2 
 *la url es http://localhost:8080/h2-console/
 *la jdbc url es jdbc:h2:mem:alumnos
 *
 *SWAGGER 
 * http://localhost:8080/swagger-ui/index.html
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
