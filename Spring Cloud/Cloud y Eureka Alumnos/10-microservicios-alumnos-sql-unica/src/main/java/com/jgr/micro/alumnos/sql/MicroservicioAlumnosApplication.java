package com.jgr.micro.alumnos.sql;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
@EntityScan({"com.jgr.commons.modelo.alumnos"})
public class MicroservicioAlumnosApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroservicioAlumnosApplication.class, args);
	}

}
