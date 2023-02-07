package com.jgr.cloud.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;


/**
 * The Class CloudConfigApplication.
 * Servidor de configuracion,toma los parametros de git
 */
@SpringBootApplication
@EnableConfigServer
public class CloudConfigApplication {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(CloudConfigApplication.class, args);
	}

}
