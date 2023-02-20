package com.jgr.curso.mongo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class CursoMongoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CursoMongoApplication.class, args);
	}

}
