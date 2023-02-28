package com.jgr.micro.alumnos.sql.models.repository.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

import com.jgr.commons.modelo.alumnos.Alumno;
import com.jgr.micro.alumnos.sql.models.repository.AlumnoRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class AlumnoRepositoryTest {
	
	/** The i alumno repository. */
	@Autowired

	private AlumnoRepository iAlumnoRepository;

	/** The limite. */
	private static int limite = 5;

	/** The alumnos lista. */
	private List<Alumno> alumnosLista;


	@BeforeEach
	void setUp() throws Exception {

		Alumno al;
		alumnosLista = new ArrayList<>();

		for (int i = 0; i < limite; i++) {
			al = new Alumno();
			al.setNombre("NombreAlumnoRepositoryTest" + i);
			al.setApellido("ApellidoAlumnoRepositoryTest" + i);
			al.setEmail("EmailAlumnoRepositoryTest" + i + "@mail.com");
			iAlumnoRepository.save(al);
			alumnosLista.add(al);
		}

	}

	@Test
	void testFindByNombreOrApellidoIgnoringCase() {
	
			Alumno al = alumnosLista.get(limite - 1);
			// le cambio el nombre a un alumno,a ver si lo encuentra
			String nombreCambiado = al.getNombre().toLowerCase();
			List<Alumno> alumnosBuscados = (List<Alumno>) iAlumnoRepository.findByNombreOrApellidoIgnoringCase(nombreCambiado);
			Alumno al2 = alumnosBuscados.get(0);
			System.out.println("alumno a buscar"+al.getId());
			System.out.println("alumno encontrado"+al2.getId());
			assertEquals(al, al2, () -> "no ha encontrado al alumno");

	}


	/**
	 * Test save.
	 */
	@Test
	@DisplayName("testSave()")
	void testSave() {

		Alumno al = new Alumno();
		al.setNombre("NombreNuevo");
		al.setApellido("ApellidoNuevo");
		al.setEmail("Email@Mail");
		Long numUltimo = ((List<Alumno>)iAlumnoRepository.findAll())
				.get(limite - 1).getId();
		
		iAlumnoRepository.save(al);
		Long altaAlumno = ((List<Alumno>)iAlumnoRepository.findAll())
				.get(limite - 1).getId();
		assertEquals(numUltimo, altaAlumno, () -> "No se ha dado de alta");

	}
	

	/**
	 * Test find by id.
	 */
	@Test
	@DisplayName("testFindById")
	void testFindById() {

		List<Alumno> listaAlumnosTrabajo = new ArrayList<>();
		listaAlumnosTrabajo = (List<Alumno>) iAlumnoRepository.findAll(Sort.by(Direction.ASC, "nombre"));
		Alumno al = listaAlumnosTrabajo.get(0);

		Optional<Alumno> al2 = iAlumnoRepository.findById(al.getId());
		assertTrue(al2.isPresent(), () -> "El alumno NO existe");
		assertEquals(al2.get(), al, () -> "No es igual que el recuperado de la BBDD");

	}
	
	/**
	 * Test delete by id.
	 */
	@Test
	@DisplayName("testDeleteById()")
	void testDeleteById() {

		Long ultimoId = alumnosLista.get(limite - 1).getId();
		iAlumnoRepository.deleteById(ultimoId);

		Optional<Alumno> al = iAlumnoRepository.findById(ultimoId);
		assertFalse(al.isPresent(), () -> "Pues no ha borrado");
		assertTrue(al.isEmpty(), () -> "no esta vacio el alumno");

	}



}
