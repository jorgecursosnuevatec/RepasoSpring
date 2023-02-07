package com.jgr.servicio.item;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.config.environment.Environment;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
//@EnableCircuitBreaker
@EnableEurekaClient
@EnableFeignClients
@SpringBootApplication

public class ItemApplication {
	

	   

	public static void main(String[] args) {
		SpringApplication.run(ItemApplication.class, args);
	}

}
