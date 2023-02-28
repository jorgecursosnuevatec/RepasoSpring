package com.jgr.micro.alumnos.sql.controllers.test;

import static org.junit.jupiter.api.Assertions.*;



import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import com.jgr.commons.modelo.alumnos.Alumno;


/**
 * The Class AlumnoControllerWebClientTest.
 * 
 * PRUEBAS DE INTEGRACION,REALES PARA WEBFLUX,PROGRAMACION REACTIVA
 * 
 * *************************NO HECHA************************************************
 * *************************NO HECHA************************************************
 * *************************NO HECHA************************************************
 * *************************NO HECHA************************************************
 * *************************NO HECHA************************************************
 * *************************NO HECHA************************************************
 * *************************NO HECHA************************************************
 * *************************NO HECHA************************************************
 * Sin mock.Se utilizan puertos reales,se levanta un servidor real
 * WebClient es para WEBFLUX,programacion reactiva
 * 
 */
//le asignamos un puerto aleatorio
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AlumnoControllerWebClientTest {


	//inyecta dependencias para probar servicicios test
	
	@Autowired
	private WebTestClient webTestClient;
	
	/**
	 * Test buscar por id.
	 */
	@Test
	void testBuscarPorId() {
		
		//se llama al servidor del 8080, donde esta el microservicio
		webTestClient.get().uri("localhost:8080l/id/1")
		.exchange()//realizamos el request
		.expectStatus().isOk()//el resultado debe ser ok
		.expectHeader().contentType(MediaType.APPLICATION_JSON) //que la cabecera sea un json
		.expectBody() //en el body tiene que devolver esto
		.jsonPath("$.nombre").isEqualTo("Nombre");
		
		
		
	}
}
