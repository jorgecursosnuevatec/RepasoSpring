/*
 * 
 */
package com.jgr.micro.alumno.test.controller;

import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jgr.micro.alumno.entity.Alumno;


/**
 * The Class AlumnoControllerRestTemplateTest.
 * 
 * ordenamos porque necesitamos que se ejecute en orden
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment=SpringBootTest.WebEnvironment.RANDOM_PORT)

class AlumnoControllerRestTemplateTest {
	
	@Autowired
	private TestRestTemplate testRestTemplate;
	
	private ObjectMapper objectMapper;

	/**
	 * Sets the up.
	 *
	 * @throws Exception the exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		
		objectMapper = new ObjectMapper();
	}
	
	
	@Value("${server.port}")

    //@LocalServerPort
    private int puerto;
	
	  private String crearUri(String uri) {
	        return "http://localhost:" + puerto + uri;
	    }

	/**
	 * Test.
	 */
	@Test
	@Order(1)
	void guardarAlumnoTest() {
		Alumno al = new Alumno();
		al.setNombre("Nombre1");
		al.setApellidos("Apellido1");
		al.setEmail("Email1@mail.com");
		al.setCreateAt(new Date());
		
		
		 ResponseEntity<Alumno> respuesta = testRestTemplate
				 .postForEntity(crearUri("/api/cuentas"), al, Alumno.class);
	        assertEquals(HttpStatus.CREATED, respuesta.getStatusCode());
	        assertEquals(MediaType.APPLICATION_JSON, respuesta.getHeaders().getContentType());
	        Alumno cuentaCreada = respuesta.getBody();
	        assertNotNull(cuentaCreada);
	        assertEquals(3L, cuentaCreada.getIdAlumno());
	        assertEquals("Pepa", cuentaCreada.getNombre());
	        assertEquals("3800", cuentaCreada.getApellidos());
	}

}
