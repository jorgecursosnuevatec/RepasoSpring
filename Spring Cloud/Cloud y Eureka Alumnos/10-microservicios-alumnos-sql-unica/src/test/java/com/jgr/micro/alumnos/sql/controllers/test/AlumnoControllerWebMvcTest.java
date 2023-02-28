package com.jgr.micro.alumnos.sql.controllers.test;


import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jgr.commons.modelo.alumnos.Alumno;
import com.jgr.micro.alumnos.sql.controllers.AlumnoController;
import com.jgr.micro.alumnos.sql.services.AlumnoServiceImpl;



/**
* The Class AlumnoControllerWebMvcTest.
* 
* se simula la llamada al controlador,MOCK, NO es real
* tambien se simula el servicio
* 
* con ObjectMapper cambiamos el formato de un objeto a/desde JSON
*
 */
@WebMvcTest(AlumnoController.class)
class AlumnoControllerWebMvcTest {
	
	/** The moc mvc. */
	@Autowired
	private MockMvc mocMvc;
	
	/** The alumno service. */
	@MockBean
	private AlumnoServiceImpl alumnoService;
	
	/** The limite. */
	private static int limite = 5;

	/** The alumnos lista. */
	private List<Alumno> alumnosLista;
	
	/** The object mapper. */
	//para convertir de json a objeto,lo inicializamos en setup
	private ObjectMapper objectMapper;

	/**
	 * Sets the up.
	 *
	 * @throws Exception the exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		Alumno al;
		alumnosLista = new ArrayList<>();

		for (int i = 0; i < limite; i++) {
			al = new Alumno();
			al.setId(Long.valueOf(i));
			al.setNombre("Nombre" + i);
			al.setApellido("Apellido" + i);
			al.setEmail("Email" + i + "@mail.com");
			alumnosLista.add(al);
		}
		
		objectMapper = new ObjectMapper();
	}


	/**
	 * Test editar.
	 */
	@Test
	void testEditar() {
		fail("Not yet implemented");
	}

	/**
	 * Test filtrar.
	 */
	@Test
	@DisplayName("testBuscarPorId()")
	void testBuscarPorId() throws Exception {
		
		//Given ->dados estos datos de prueba
		when(alumnoService.findById(1L)).thenReturn(Optional.of(alumnosLista.get(1)));
		
//		log.info("buscarPorId valor->"+alumnosLista.get(1));
		
		//When-> cuando hacemos esta llamada
		mocMvc.perform(get("/id/1") //llamamos al buscar por id
				.contentType(MediaType.APPLICATION_JSON)) //el contenttype que enviamos
		
		//Then-> esperamos esto
				.andExpect(status().isOk()) //esperamos este resultado
				.andExpect(content().contentType(MediaType.APPLICATION_JSON)) //la respuesta debe ser un json
				.andExpect(jsonPath("$.id").value(1L))//vemos que los atributos coinciden
				.andExpect(jsonPath("$.nombre").value("Nombre1"))//vemos que los atributos coinciden
				; 
		//nos aseguramos que estamos probando la llamada a la capa de servicio
		verify(alumnoService).findById(1L);
		
		
		//pasamos el alumno a texto para compararlo luego
		Alumno al = alumnoService.findById(1L).get();
		
		//ahora probamos convirtiendo el objeto a string para comparar que es lo que devuelve
		mocMvc.perform(get("/id/1") //llamamos al buscar por id
				.contentType(MediaType.APPLICATION_JSON) //el contenttype que enviamos
			    .content(objectMapper.writeValueAsString(al))) //pasamos el alumno que enviamos a formato texto
		
		
			    //esto es lo que esperamos de resultado
			    .andExpect(status().isOk()) //esperamos este resultado
			    .andExpect(content().contentType(MediaType.APPLICATION_JSON)) //la respuesta debe ser un json
			    .andExpect(jsonPath("$.id").value(1L))
			    .andExpect(jsonPath("$.nombre").value("Nombre1"))
			    ;
		
	
	}

	

	

	/**
	 * Test listar.
	 * @throws Exception 
	 * @throws JsonProcessingException 
	 */
	@Test
	@DisplayName("testListarTodos()")
	void testListar() throws JsonProcessingException, Exception {
		  
        when(alumnoService.findAll()).thenReturn(alumnosLista);

        // When
        mocMvc.perform(get("/").contentType(MediaType.APPLICATION_JSON))
        // Then
        .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].nombre").value("Nombre0"))              
                .andExpect(jsonPath("$", hasSize(5)))
                .andExpect(content().json(objectMapper.writeValueAsString(alumnosLista)));

        verify(alumnoService).findAll();
	}

	

	

	/**
	 * Test crear.
	 * @throws Exception 
	 * @throws JsonProcessingException 
	 */
	@Test
	@DisplayName("testGuardar()")
	void testCrear() throws JsonProcessingException, Exception {
		  // Given
        
		//¿hay que trampearlo,porque como se hacen validaciones de si esta relleno en el controller
		//como no lo cargue antes da error? Si no fuera por el @Valid/BindingResult no haria falta
		 Alumno alumno = new Alumno();
		 alumno.setId(99999L);
		 alumno.setNombre("Pepe");
		 alumno.setApellido("ApellidoPepe");
		 alumno.setEmail("email@mail.com");
		 alumno.setCreateAt(new Date());
		 alumno.setFoto(null);
         
       
       when(alumnoService.save(any())).then(invocation ->{
           Alumno al = invocation.getArgument(0);
           al.setId(99999L);
           al.setNombre("Pepe");
           al.setApellido("ApellidoPepe");
           al.setEmail("email@mail.com");
           al.setCreateAt(new Date());
           al.setFoto(null);
           
           return al;
       });
       

       // when
       mocMvc.perform(post("/").contentType(MediaType.APPLICATION_JSON)
               .content(objectMapper.writeValueAsString(alumno)))
       // Then
               .andExpect(status().isCreated())
               .andExpect(content().contentType(MediaType.APPLICATION_JSON))
               .andExpect(jsonPath("$.id", is(99999)))
               .andExpect(jsonPath("$.nombre", is("Pepe")));
               
   }
	

	

	

}
