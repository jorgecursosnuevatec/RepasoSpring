package com.jgr.micro.alumnos.sql.controllers.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.jgr.commons.modelo.alumnos.Alumno;


/**
 * The Class AlumnoControllerTestRestTemplate.
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment=SpringBootTest.WebEnvironment.RANDOM_PORT)
class AlumnoControllerTestRestTemplate {

	/** The test rest template. */
	@Autowired
	private TestRestTemplate testRestTemplate;
	
	/** The object mapper. */
	private ObjectMapper objectMapper;
	
    /** The puerto. */
    //@LocalServerPort
    private int puerto;
    
    /** The server port. */
    @LocalServerPort
    private int serverPort;
	
	
	/** The server properties. */
	@Autowired
	private ServerProperties serverProperties;
	
	
	/** The web server app ctxt. */
	@Autowired
	private ServletWebServerApplicationContext webServerAppCtxt;
	
	
	/** The gson. */
	private Gson gson;

	/**
	 * Sets the up.
	 *
	 * @throws Exception the exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		
		objectMapper = new ObjectMapper();		
		puerto = webServerAppCtxt.getWebServer().getPort();
		System.out.println("******PUERTO webServerAppCtxt->"+webServerAppCtxt.getWebServer().getPort());
		System.out.println("******PUERTO serverPort->"+serverPort);
		gson=new Gson();
	}
	

	
	  /**
	 * Crear uri.
	 *formo la uri para las peticiones al controlador
	 * @param uri the uri
	 * @return the string
	 */
	private String crearUri(String uri) {
	        return "http://localhost:" + puerto + uri;
	    }


	/**
	 * Test editar.
	 */
	@Test
	void testCrear() {
		Alumno al = new Alumno();
		al.setNombre("Nombre1");
		al.setApellido("Apellido1");
		al.setEmail("Email999@mail.com");
		al.setCreateAt(new Date());
		
		 ResponseEntity<Alumno> respuesta = testRestTemplate
				 .postForEntity(crearUri("/"), al, Alumno.class);
	        assertEquals(HttpStatus.CREATED, respuesta.getStatusCode());
	        assertEquals(MediaType.APPLICATION_JSON, respuesta.getHeaders().getContentType());
	        Alumno cuentaCreada = respuesta.getBody();
	        System.out.println("el alumno es->"+cuentaCreada);
	        assertNotNull(cuentaCreada);
//	        assertNotNull(cuentaCreada.getIdAlumno(),()->"El id del alumno es nulo");
	        assertEquals("Nombre1", cuentaCreada.getNombre());
	        assertEquals("Apellido1", cuentaCreada.getApellido());
		
	}

	/**
	 * Test filtrar.
	 */
	@Test
	void testFiltrar() {
		fail("Not yet implemented");
	}

	/**
	 * Test listar.
	 */
	@Test
	@DisplayName("testListar()")
	void testListar() {
		
		ResponseEntity<Alumno[]> respuesta = 
				testRestTemplate
				.getForEntity(crearUri("/"), Alumno[].class);;
		
				
		assertNotNull(respuesta);		


		//devueleve un array  de alumnos, deberia estar ordenada por id

		
		List<Alumno> alumnosLista = Stream.of(respuesta.getBody())
				.map(alumno->{    //le paso el formato a LISTA de alumnos
					Alumno al= new Alumno();
					al.setId(alumno.getId());
					al.setNombre(alumno.getNombre());
					al.setApellido(alumno.getApellido());
					al.setCreateAt(alumno.getCreateAt());
					al.setEmail(alumno.getEmail());
					al.setFoto(al.getFoto());
					return al;
				})
				.sorted((a1,a2)->a1.getId().compareTo(a2.getId()))//ordeno por id
				.collect(Collectors.toList()); //a lista
	
		
	
		assertEquals(HttpStatus.OK, respuesta.getStatusCode());
		assertEquals(MediaType.APPLICATION_JSON, respuesta.getHeaders().getContentType());
		assertTrue(alumnosLista.size()>0,()->"Ha devuelto lista vacia");
		assertEquals(alumnosLista.get(0).getId(),(1L));
	}

	/**
	 * Test eliminar.
	 */
	@Test
	void testEliminar() {
		fail("Not yet implemented");
	}

}
