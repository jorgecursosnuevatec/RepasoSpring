package com.jgr.servicio.item;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.config.environment.Environment;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;


/**
 * The Class AppConfigClienteRest.
 */
@Configuration
public class AppConfigClienteRest {

	/**
	 * Registrar rest template.
	 *
	 * @return the rest template
	 */
	@Bean("clienteRest")
	@LoadBalanced
	public RestTemplate registrarRestTemplate() {
		return new RestTemplate();
	}
	
	
	
	
	
	


	
}
