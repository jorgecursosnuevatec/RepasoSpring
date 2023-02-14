/*
 * 
 */
package com.jgr.servicio.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;


/**
 * The Class ApplicationEureka.
 * IMPORTANTE INCLUIR DEPENDENCIA <groupId>org.glassfish.jaxb</groupId>/<artifactId>jaxb-runtime</artifactId>
 * A PARTIR DE JAVA 9
 * 
 */
@SpringBootApplication
@EnableEurekaServer
public class ApplicationEureka {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(ApplicationEureka.class, args);
	}

}
