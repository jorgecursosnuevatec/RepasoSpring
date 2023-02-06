package com.jgr.gateway.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


/**
 * The Class GatewayApplication.
 * 
 * en la clase GatewayFilters añadimos unos filtros, en el pre añadimos cookies a la cabecera
 * Y cambiamos el formato de salida, podemos cambiar de json a texto plano
 * para el resilience4j hay que añadir reactor a lo que pone por defecto
 * spring-cloud-starter-circuitbreaker-reactor-resilience4j
 * 
 * 
 */
@SpringBootApplication
@EnableDiscoveryClient
public class GatewayApplication {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(GatewayApplication.class, args);
	}

}
