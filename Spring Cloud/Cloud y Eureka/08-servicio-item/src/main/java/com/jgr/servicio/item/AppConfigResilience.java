package com.jgr.servicio.item;

import java.time.Duration;

import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreakerFactory;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JConfigBuilder;
import org.springframework.cloud.client.circuitbreaker.Customizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;

/**
 * The Class AppConfigResilience.
 * Configuramos el Resilience4J
 * esto se aplicaria a todas las llamadas
 * LO PODEMOS HACER EN EL PROPERTIES, QUE TIENEN PRIORIDAD SOBRE ESTA CLASE
 */
@Configuration
public class AppConfigResilience{
	
	
	/**
	 * Por defecto.
	 * personalizamos el numero de ventanas que cuenta,la tasa de error,el tiempo que va a estar abierto...
	 *
	 * @return the customizer
	 */
	////////////////////////////////////////////@Bean
	public Customizer<Resilience4JCircuitBreakerFactory> porDefecto(){
		return factory->factory
				.configureDefault(i->{
					return new Resilience4JConfigBuilder(i)
							.circuitBreakerConfig(CircuitBreakerConfig.custom()
									.slidingWindowSize(5) //el numero de ventanas que cuenta
									.failureRateThreshold(50)//tasa de error 50%
									.waitDurationInOpenState(Duration.ofMillis(10L))//tiempo de espera abierto
									.permittedNumberOfCallsInHalfOpenState(5)//numero de llamadas
									.slowCallRateThreshold(50) //porcentajde de llamadas lentas,el tiempo debajo
									.slowCallDurationThreshold(Duration.ofMillis(10L))//									
									.build())//solo cuando se personaliza
							.timeLimiterConfig(TimeLimiterConfig.custom()//lo que permitimos como duracion maxima para timeout
									.timeoutDuration(Duration.ofMillis(10L)).build())							
							.build();
				});
		
	}

}
