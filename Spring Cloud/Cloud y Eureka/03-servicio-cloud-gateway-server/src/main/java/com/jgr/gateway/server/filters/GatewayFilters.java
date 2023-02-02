package com.jgr.gateway.server.filters;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

/**
 * The Class GatewayFilters.
 * 
 * Filtros Gateway
 * El GlobalFilter es la interfaz que se usa para implementar los metodos a usar
 * en el filtro
 * el Ordered es para indicar el orden de precedencia en los filtros,por si hay mas de uno
 */

@Component
public class GatewayFilters implements GlobalFilter,Ordered{
	
	private final Logger logger = LoggerFactory.getLogger(GatewayFilters.class);

	/**
	 * Filter.
	 * Spring Cloud lo gestiona por detras con programacion reactiva
	 * Este es un filtro generico
	 *
	 * @param exchange the exchange
	 * contiene el request y el response de la peticion
	 * 
	 * @param chain the chain
	 * 
	 * @return the mono
	 */
	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		
		//prefilter 
		logger.info("ejecutando filtro PRE en GatewayFilters");
		
		//añadimos el token a la cabecera
		exchange.getRequest()
		.mutate()
		.headers(h-> h.add("tokenCabecera", "valortokenCabecera"));
		
		
		//continua con la ejecucion de la peticion actual,lo que esta dentro del .then seria el post
		return chain.filter(exchange)
				.then(Mono.fromRunnable(()->{ // permite crear un mono<Void>(programacion reactiva)
					logger.info(logger.getClass()+"en el filtro post dentro de GatewayFilters");
					
					//añadimos a la cabecera de salida el token que hemos creado
					Optional.ofNullable(exchange.getRequest()
							.getHeaders().getFirst("tokenCabeceraGatewayFilters"))
					.ifPresent(valorQueLeDamos->{
						exchange.getResponse()
						.getHeaders().add("tokenCabecera", valorQueLeDamos);
					});
					
					exchange.getResponse()
					.getCookies()//añadimos una cookie
					.add("galleta", ResponseCookie.from("galleta", "valorCookieGatewayFilters").build());
					
					exchange.getResponse() //lo añadimos a la cabecera
					.getHeaders().setContentType(MediaType.APPLICATION_JSON);//formato de la respuesta(texto,json...)
				})); //cuando ha terminado la ejecucion,ya seria el post
	}

	@Override
	public int getOrder() {
		
		return 1;
	}
	

}
