package com.jgr.micro.alumnos.sql;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


/**
 * The Class MicroservicioAlumnosApplication.
 * 
 * ACTUALIZAR PUERTO CON EL QUE SE EJECUTE PARA VER DOCUMENTACION
 * 
 * Documentado como JSON CONTROLLER->localhost:8080/v3/api-docs
 * Documentado como HTML CONTROLLER->http://localhost:8080/swagger-ui/index.html
 * 
 * añadido cache
 */
@EnableEurekaClient
@SpringBootApplication
@EntityScan({"com.jgr.commons.modelo.alumnos"})
public class MicroservicioAlumnosApplication {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(MicroservicioAlumnosApplication.class, args);
	}

}
