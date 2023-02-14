package com.jgr.micro.alumno.test.controller;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

/**
 * The Class AlumnoControllerWebClientTest.
 * 
 * PRUEBAS DE INTEGRACION,REALES PARA WEBFLUX,PROGRAMACION REACTIVA
 * 
 * *************************NO HECHA************************************************
 * Sin mock.Se utilizan puertos reales,se levanta un servidor real
 * WebClient es para WEBFLUX,programacion reactiva
 * 
 */
//le asignamos un puerto aleatorio
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AlumnoControllerWebClientTest {
	
	/** The Constant log. */
	private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(AlumnoControllerWebClientTest.class);

	//inyecta dependencias para probar servicicios test
	
	@Autowired
	private WebTestClient webTestClient;
	/**
	 * Sets the up.
	 *
	 * @throws Exception the exception
	 */
	@BeforeEach
	void setUp() throws Exception {
	}

	/**
	 * Test listar todos.
	 */
	@Test
	void testListarTodos() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test buscar por id.
	 */
	@Test
	void testBuscarPorId() {
		
		//se llama al servidor del 8080, donde esta el microservicio
		webTestClient.get().uri("localhost:8080/id/1")
		.exchange()//realizamos el request
		.expectStatus().isOk()//el resultado debe ser ok
		.expectHeader().contentType(MediaType.APPLICATION_JSON) //que la cabecera sea un json
		.expectBody() //en el body tiene que devolver esto
		.jsonPath("$.nombre").isEqualTo("Nombre");
		
		
		
	}

	/**
	 * Test find by nombre like ignore case.
	 */
	@Test
	void testFindByNombreLikeIgnoreCase() {
		
	}

	/**
	 * Test actualiza alumno.
	 */
	@Test
	void testActualizaAlumno() {
		
	}

	/**
	 * Test busca nombre or apellido.
	 */
	@Test
	void testBuscaNombreOrApellido() {
		
	}

	/**
	 * Test find by nombre or apellidos containing ignore case.
	 */
	@Test
	void testFindByNombreOrApellidosContainingIgnoreCase() {
		
	}

	/**
	 * Test ver foto.
	 */
	@Test
	void testVerFoto() {
	
	}


	/**
	 * Test guardar alumno.
	 */
	@Test
	void testGuardarAlumno() {
		
	}

}
