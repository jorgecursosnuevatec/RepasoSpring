/*
 * 
 */
package com.jgr.micro.alumno.test.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.lenient;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.jgr.common.alumno.model.Alumno;
import com.jgr.micro.alumno.repository.IAlumnoRepository;
import com.jgr.micro.alumno.service.AlumnoServiceImpl;

// TODO: Auto-generated Javadoc
//https://stackoverflow.com/questions/60308578/what-is-the-difference-between-extendwithspringextension-class-and-extendwit
/**
 * The Class AlumnoServiceImplTest.
 * 
 * USO MOCKITOEXTENSION MARCO COMO LENIENT ALGUNOS METODOS PARA QUE NO DE ERROR
 */
//@Slf4j 
@ExtendWith(MockitoExtension.class)

class AlumnoServiceImplTest {

	/** The Constant log. */
	private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(AlumnoServiceImplTest.class);

	/** The i alumno repository. */
	@Mock
	IAlumnoRepository iAlumnoRepository;

	/** The alumno service. */
	@InjectMocks
	AlumnoServiceImpl alumnoService;

	/** The al 1. */
	Optional<com.jgr.common.alumno.model.Alumno> al1;

	/** The al 2. */
	Optional<com.jgr.common.alumno.model.Alumno> al2;
	
	/** The al 3. */
	com.jgr.common.alumno.model.Alumno al3;

	/** The alumnos lista. */
	List<com.jgr.common.alumno.model.Alumno> alumnosLista;

	/** The limite. */
	int limite = 3;

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
			al = new Alumno();
			al.setIdAlumno(Long.valueOf(i));
			al.setNombre("Nombre" + i);
			al.setApellidos("Apellido" + i);
			al.setCreateAt(new Date());
			al.setEmail("email" + i + "@mail.com");
			alumnosLista.add(al);
		}

		al1 = Optional.ofNullable(alumnosLista.get(1));
		al2 = Optional.ofNullable(alumnosLista.get(2));

		al3 = new Alumno();
		al3.setIdAlumno(Long.valueOf(3));
		al3.setNombre("Nombre" + 3);
		al3.setApellidos("Apellido" + 3);
		al3.setCreateAt(new Date());
		al3.setEmail("email" + 3 + "@mail.com");

		lenient().when(iAlumnoRepository.findAll()).thenReturn(alumnosLista);
		lenient().when(iAlumnoRepository.findById(1L)).thenReturn(al1);
		lenient().when(iAlumnoRepository.findById(2L)).thenReturn(al2);

		// cuando hago un save de cualquier alumno le digo que devuelva el 3,igual que
		// el que hemos creado aqui como numero 3
		lenient().when(iAlumnoRepository.save(any(Alumno.class))).thenReturn(al3);

		lenient().when(iAlumnoRepository.findByNombreContainsIgnoreCase(any(String.class))).thenReturn(alumnosLista);

	}

	/**
	 * Test find all.
	 */
	@Test
	@DisplayName("testFindAll()")
	void testFindAll() {

		// log.debug("En testFindAll"+alumnoService.findAll());
		log.warn("En testFindAll" + alumnoService.findAll());
		assertEquals(alumnoService.findAll(), alumnosLista, () -> "No son iguales");

	}

	/**
	 * Test find by id.
	 */
	@Test
	@DisplayName("testFindById()")
	void testFindById() {
		assertEquals(alumnoService.findById(1L), al1, () -> "No son iguales");
	}

	/**
	 * Test save.
	 */
	@Test
	@DisplayName("testSave()")
	void testSave() {

		// puede dar distinto por la fecha
		Alumno alNuevo = al3;
		assertTrue(alumnoService.save(alNuevo).equals(al3),
				() -> "No son iguales" + alumnoService.save(alNuevo) + " no es igual" + al3);
		
		

	}

	/**
	 * Test delete by id.
	 */
	@Test
	@DisplayName("testDeleteById()")
	void testDeleteById() {

	}

	/**
	 * Test find by nombre like ignore case.
	 */
	@Test
	@DisplayName("testFindByNombreLikeIgnoreCase()")
	void testFindByNombreLikeIgnoreCase() {

		assertEquals(alumnoService.findAll(), alumnosLista, () -> "No son iguales");

	}

	/*
	 */
}
