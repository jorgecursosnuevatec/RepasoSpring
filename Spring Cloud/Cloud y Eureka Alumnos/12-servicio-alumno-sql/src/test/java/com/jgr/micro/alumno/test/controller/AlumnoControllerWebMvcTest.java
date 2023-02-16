/**
 * 
 */
package com.jgr.micro.alumno.test.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
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
import com.jgr.micro.alumno.controller.AlumnoController;

import com.jgr.micro.alumno.service.AlumnoServiceImpl;


// TODO: Auto-generated Javadoc
/**
 * The Class AlumnoControllerWebMvcTest.
 * 
 * se simula la llamada al controlador,MOCK, NO es real
 * tambien se simula el servicio
 * 
 * con ObjectMapper cambiamos el formato de un objeto a/desde JSON
 *
 * @author JORGE
 */

@WebMvcTest(AlumnoController.class)
class AlumnoControllerWebMvcTest {
	
	/** The Constant log. */
	private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(AlumnoControllerWebMvcTest.class);
	
	/** The moc mvc. */
	@Autowired
	private MockMvc mocMvc;
	
	/** The alumno service. */
	@MockBean
	private AlumnoServiceImpl alumnoService;
	
	/** The limite. */
	private static int limite = 5;

	/** The alumnos lista. */
	private List<com.jgr.common.alumno.model.Alumno> alumnosLista;
	
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
		
		com.jgr.common.alumno.model.Alumno al;
		alumnosLista = new ArrayList<>();

		for (int i = 0; i < limite; i++) {
			al = new com.jgr.common.alumno.model.Alumno();
			al.setIdAlumno(Long.valueOf(i));
			al.setNombre("Nombre" + i);
			al.setApellidos("Apellido" + i);
			al.setEmail("Email" + i + "@mail.com");
			alumnosLista.add(al);
		}
		
		objectMapper = new ObjectMapper();
		
	}

	/**
	 * Test method for
	 * {@link com.jgr.micro.alumno.controller.AlumnoController#listarTodos()}.
	 *
	 * @throws JsonProcessingException the json processing exception
	 * @throws Exception               the exception
	 */
	@Test
	@DisplayName("testListarTodos()")
	void testListarTodos() throws JsonProcessingException, Exception {
		// Given
        
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
	 * Test method for
	 * {@link com.jgr.micro.alumno.controller.AlumnoController#buscarPorId(java.lang.Long)}.
	 *
	 * @throws Exception the exception
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
				.andExpect(jsonPath("$.idAlumno").value(1L))//vemos que los atributos coinciden
				.andExpect(jsonPath("$.nombre").value("Nombre1"))//vemos que los atributos coinciden
				; 
		//nos aseguramos que estamos probando la llamada a la capa de servicio
		verify(alumnoService).findById(1L);
		
		
		//pasamos el alumno a texto para compararlo luego
		com.jgr.common.alumno.model.Alumno al = alumnoService.findById(1L).get();
		
		//ahora probamos convirtiendo el objeto a string para comparar que es lo que devuelve
		mocMvc.perform(get("/id/1") //llamamos al buscar por id
				.contentType(MediaType.APPLICATION_JSON) //el contenttype que enviamos
			    .content(objectMapper.writeValueAsString(al))) //pasamos el alumno que enviamos a formato texto
		
		
			    //esto es lo que esperamos de resultado
			    .andExpect(status().isOk()) //esperamos este resultado
			    .andExpect(content().contentType(MediaType.APPLICATION_JSON)) //la respuesta debe ser un json
			    .andExpect(jsonPath("$.idAlumno").value(1L))
			    .andExpect(jsonPath("$.nombre").value("Nombre1"))
			    ;
		
		
	}
	
	/**
	 * Test guardar.
	 *
	 * @throws Exception the exception
	 */
	@Test
	@DisplayName("testGuardar()")
    void testGuardar() throws Exception {
        // Given
        
		 com.jgr.common.alumno.model.Alumno alumno = new com.jgr.common.alumno.model.Alumno();
        
        when(alumnoService.save(any())).then(invocation ->{
            com.jgr.common.alumno.model.Alumno al = invocation.getArgument(0);
            al.setIdAlumno(99999L);
            al.setNombre("Pepe");
            al.setApellidos("ApellidoPepe");
            al.setEmail("email@mail.com");
            return al;
        });

        // when
        mocMvc.perform(post("/guardaNormal").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(alumno)))
        // Then
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.idAlumno", is(99999)))
                .andExpect(jsonPath("$.nombre", is("Pepe")));
                
    }

	

}
