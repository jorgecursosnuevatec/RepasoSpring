/*
 * 
 */
package com.jgr.micro.alumno.test.controller;

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
import com.jgr.micro.alumno.entity.Alumno;


// TODO: Auto-generated Javadoc
/**
 * The Class AlumnoControllerRestTemplateTest.
 * 
 * ordenamos porque necesitamos que se ejecute en orden
 * 
 * 
 * 
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment=SpringBootTest.WebEnvironment.RANDOM_PORT)

class AlumnoControllerRestTemplateTest {
	
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
	 * 
	 * obtengo el puerto, es aleatorio por eso tengo que obtenerlo en cada ejecucion
	 * 
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
	 * Test.
	 */
	@Test
//	@Order(1)
	@DisplayName("guardarAlumnoTest()")
	void guardarAlumnoTest() {
		
		Alumno al = new Alumno();
		al.setNombre("Nombre1");
		al.setApellidos("Apellido1");
		al.setEmail("Email1@mail.com");
		al.setCreateAt(new Date());
		
		 ResponseEntity<Alumno> respuesta = testRestTemplate
				 .postForEntity(crearUri("/guardaNormal"), al, Alumno.class);
	        assertEquals(HttpStatus.CREATED, respuesta.getStatusCode());
	        assertEquals(MediaType.APPLICATION_JSON, respuesta.getHeaders().getContentType());
	        Alumno cuentaCreada = respuesta.getBody();
	        System.out.println("el alumno es->"+cuentaCreada);
	        assertNotNull(cuentaCreada);
//	        assertNotNull(cuentaCreada.getIdAlumno(),()->"El id del alumno es nulo");
	        assertEquals("Nombre1", cuentaCreada.getNombre());
	        assertEquals("Apellido1", cuentaCreada.getApellidos());
	    
	}
	
	/**
	 * Listar todos test.
	 */
	@Test
//	@Order(1)
	@DisplayName("listarTodosTest()")
	void listarTodosTest() {
		
		ResponseEntity<Alumno[]> respuesta = 
				testRestTemplate
				.getForEntity(crearUri("/"), Alumno[].class);;
		
				
		assertNotNull(respuesta);		


		//devueleve un array  de alumnos, deberia estar ordenada por id

		
		List<Alumno> alumnosLista = Stream.of(respuesta.getBody())
				.map(alumno->{    //le paso el formato a LISTA de alumnos
					Alumno al= new Alumno();
					al.setIdAlumno(alumno.getIdAlumno());
					al.setNombre(alumno.getNombre());
					al.setApellidos(alumno.getApellidos());
					al.setCreateAt(alumno.getCreateAt());
					al.setEmail(alumno.getEmail());
					al.setFoto(al.getFoto());
					return al;
				})
				.sorted((a1,a2)->a1.getIdAlumno().compareTo(a2.getIdAlumno()))//ordeno por id
				.collect(Collectors.toList()); //a lista
	
		
	
		assertEquals(HttpStatus.OK, respuesta.getStatusCode());
		assertEquals(MediaType.APPLICATION_JSON, respuesta.getHeaders().getContentType());
		assertTrue(alumnosLista.size()>0,()->"Ha devuelto lista vacia");
		assertEquals(alumnosLista.get(0).getIdAlumno(),(1L));
				
	}
	
	/**
	 * Borrar alumno test.
	 * devuelve void, lo que pruebo es que el array despues de borrar sea menor que el anterior
	 */
	@DisplayName("borrarAlumnoTest()")
	@Test
	void borrarAlumnoTest() {
		
		int longAntes = testRestTemplate
				.getForEntity(crearUri("/"), Alumno[].class).getBody().length;
		
		//borro
		testRestTemplate.delete(crearUri("/1"));
		
		int longDespues =testRestTemplate
				.getForEntity(crearUri("/"), Alumno[].class).getBody().length;
		
		
		assertEquals(longAntes,++longDespues,()->"No ha borrado bien el registro 1");
	       
	     
	    
	}
	
	

}
