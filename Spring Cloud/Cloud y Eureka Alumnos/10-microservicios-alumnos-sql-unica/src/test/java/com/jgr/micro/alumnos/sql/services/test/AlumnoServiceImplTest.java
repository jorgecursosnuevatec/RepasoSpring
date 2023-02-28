package com.jgr.micro.alumnos.sql.services.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
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

import com.jgr.commons.modelo.alumnos.Alumno;
import com.jgr.micro.alumnos.sql.models.repository.AlumnoRepository;
import com.jgr.micro.alumnos.sql.services.AlumnoServiceImpl;


//TODO: Auto-generated Javadoc
//https://stackoverflow.com/questions/60308578/what-is-the-difference-between-extendwithspringextension-class-and-extendwit
/**
 * The Class AlumnoServiceImplTest.
 * 
 * USO MOCKITOEXTENSION MARCO COMO LENIENT ALGUNOS METODOS PARA QUE NO DE ERROR
 */
//@Slf4j 
@ExtendWith(MockitoExtension.class)
class AlumnoServiceImplTest {


	/** The i alumno repository. */
	@Mock
	AlumnoRepository iAlumnoRepository;

	/** The alumno service. */
	@InjectMocks
	AlumnoServiceImpl alumnoService;

	/** The al 1. */
	Optional<Alumno> al1;

	/** The al 2. */
	Optional<Alumno> al2;

	/** The al 3. */
	Alumno al3;

	/** The alumnos lista. */
	List<Alumno> alumnosLista;

	/** The limite. */
	int limite = 3;


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
			al.setCreateAt(new Date());
			al.setEmail("email" + i + "@mail.com");
			alumnosLista.add(al);
		}

		al1 = Optional.ofNullable(alumnosLista.get(1));
		al2 = Optional.ofNullable(alumnosLista.get(2));

		al3 = new Alumno();
		al3.setId(Long.valueOf(3));
		al3.setNombre("Nombre" + 3);
		al3.setApellido("Apellido" + 3);
		al3.setCreateAt(new Date());
		al3.setEmail("email" + 3 + "@mail.com");

		lenient().when(iAlumnoRepository.findAll()).thenReturn(alumnosLista);
		lenient().when(iAlumnoRepository.findById(1L)).thenReturn(al1);
		lenient().when(iAlumnoRepository.findById(2L)).thenReturn(al2);

		// cuando hago un save de cualquier alumno le digo que devuelva el 3,igual que
		// el que hemos creado aqui como numero 3
		lenient().when(iAlumnoRepository.save(any(Alumno.class))).thenReturn(al3);

		lenient().when(iAlumnoRepository.findByNombreOrApellidoIgnoringCase(any(String.class))).thenReturn(alumnosLista);
	}

	/**
	 * Test find by nombre or apellido ignoring case.
	 */
	@Test
	void testFindByNombreOrApellidoIgnoringCase() {
		assertEquals(alumnoService.findAll(), alumnosLista, () -> "No son iguales");
	}

	/**
	 * Test find all.
	 */
	@Test
	void testFindAll() {
		assertEquals(alumnoService.findAll(), alumnosLista, () -> "No son iguales");

	}

	/**
	 * Test find by id.
	 */
	@Test
	void testFindById() {
		assertEquals(alumnoService.findById(1L), al1, () -> "No son iguales");
	}
	
	@Test
	@DisplayName("testSave()")
	void testSave() {

		// puede dar distinto por la fecha
		Alumno alNuevo = al3;
		assertTrue(alumnoService.save(alNuevo).equals(al3),
				() -> "No son iguales" + alumnoService.save(alNuevo) + " no es igual" + al3);
		
		

	}

}
