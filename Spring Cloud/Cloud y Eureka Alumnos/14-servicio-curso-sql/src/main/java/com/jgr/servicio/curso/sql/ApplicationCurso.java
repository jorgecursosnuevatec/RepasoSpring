package com.jgr.servicio.curso.sql;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@EntityScan(
		{"com.jgr.model.sql.alumno.entity",
			"com.jgr.servicio.curso.sql.entity"}
		
		)
public class ApplicationCurso {

	public static void main(String[] args) {
		SpringApplication.run(ApplicationCurso.class, args);
	}

}
