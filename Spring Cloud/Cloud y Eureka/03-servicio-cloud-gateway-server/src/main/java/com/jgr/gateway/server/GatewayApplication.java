package com.jgr.gateway.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


/**
 * The Class GatewayApplication.
 * 
 * en la clase GatewayFilters añadimos unos filtros, en el pre añadimos cookies a la cabecera
 * Y cambiamos el formato de salida, podemos cambiar de json a texto plano
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
