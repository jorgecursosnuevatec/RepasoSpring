/*
 * 
 */
package com.jgr.servicio.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


/**
 * The Class ApplicationGateway.
 */
@SpringBootApplication
@EnableEurekaClient
public class ApplicationGateway {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(ApplicationGateway.class, args);
	}

}
