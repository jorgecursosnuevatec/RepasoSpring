package com.jgr.micro.alumno;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;


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
@EnableEurekaClient


@ComponentScan(basePackages = {"com.jgr.common.alumno.model"})
public class ApplicationAlumno {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(ApplicationAlumno.class, args);
	}

}
