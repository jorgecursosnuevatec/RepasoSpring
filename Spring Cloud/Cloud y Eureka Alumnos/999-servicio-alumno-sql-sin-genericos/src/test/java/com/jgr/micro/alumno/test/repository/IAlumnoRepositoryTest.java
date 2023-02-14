package com.jgr.micro.alumno.test.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

import com.jgr.micro.alumno.entity.Alumno;
import com.jgr.micro.alumno.repository.IAlumnoRepository;



/**
 * The Class IAlumnoRepositoryTest.
 */
@DataJpaTest
class IAlumnoRepositoryTest {
	/** The Constant log. */
	private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(IAlumnoRepositoryTest.class);
	
	/** The i alumno repository. */
	@Autowired

	private IAlumnoRepository iAlumnoRepository;

	/** The limite. */
	private static int limite = 5;

	/** The alumnos lista. */
	private List<Alumno> alumnosLista;

	/**
	 * Sets the up. Solo una vez para todas las ejecuciones
	 *
	 * @throws Exception the exception
	 */
	@BeforeEach
	void setUp() throws Exception {

		Alumno al;
		alumnosLista = new ArrayList<>();

		for (int i = 0; i < limite; i++) {
			al = new Alumno();
			al.setNombre("Nombre" + i);
			al.setApellidos("Apellido" + i);
			al.setEmail("Email" + i + "@mail.com");
			iAlumnoRepository.save(al);
			alumnosLista.add(al);
		}

	}

	/**
	 * Test find by nombre like ignore case.
	 */
	@Test
	@DisplayName("testFindByNombreLikeIgnoreCase()")
	void testFindByNombreLikeIgnoreCase() {

		Alumno al = alumnosLista.get(limite - 1);
		// le cambio el nombre a un alumno,a ver si lo encuentra
		String nombreCambiado = al.getNombre().toLowerCase();
		List<Alumno> alumnosBuscados = (List<Alumno>) iAlumnoRepository.findByNombreContainsIgnoreCase(nombreCambiado);
		Alumno al2 = alumnosBuscados.get(0);
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
		al.setApellidos("ApellidoNuevo");
		al.setEmail("Email@Mail");
		Long numUltimo = alumnosLista.get(limite - 1).getIdAlumno();
		iAlumnoRepository.save(al);
		Long altaAlumno = iAlumnoRepository.findAll().get(limite - 1).getIdAlumno();
		assertEquals(numUltimo, altaAlumno, () -> "No se ha dado de alta");

	}

	/**
	 * Test find by id.
	 */
	@Test
	@DisplayName("testFindById")
	void testFindById() {

		List<Alumno> listaAlumnosTrabajo = new ArrayList<>();
		listaAlumnosTrabajo = iAlumnoRepository.findAll(Sort.by(Direction.ASC, "nombre"));
		Alumno al = listaAlumnosTrabajo.get(0);

		Optional<Alumno> al2 = iAlumnoRepository.findById(al.getIdAlumno());
		assertTrue(al2.isPresent(), () -> "El alumno NO existe");
		assertEquals(al2.get(), al, () -> "No es igual que el recuperado de la BBDD");

	}

	/**
	 * Test delete by id.
	 */
	@Test
	@DisplayName("testDeleteById()")
	void testDeleteById() {

		Long ultimoId = alumnosLista.get(limite - 1).getIdAlumno();
		iAlumnoRepository.deleteById(ultimoId);

		Optional<Alumno> al = iAlumnoRepository.findById(ultimoId);
		assertFalse(al.isPresent(), () -> "Pues no ha borrado");
		assertTrue(al.isEmpty(), () -> "no esta vacio el alumno");

	}
	
	@Test
	@DisplayName("testFindByNombreContainsIgnoreCase()")
	void testFindByNombreContainsIgnoreCase() {
		
		Alumno al = new Alumno();
		al.setNombre("NOMBREDELNUEVOALUMNO");
		al.setApellidos("APELLIDODELNUEVOALUMNO");
		al.setEmail("emailnuevo@mail.com");
		al.setCreateAt(new Date());
		Alumno alGuardado=iAlumnoRepository.save(al);
		
		// le cambio el nombre a un alumno,a ver si lo encuentra
		String nombreCambiado = al.getNombre().toLowerCase();
		
		Alumno alNuevo  = ((List<Alumno>) iAlumnoRepository.findByNombreContainsIgnoreCase(nombreCambiado)).get(0);

		assertEquals(alGuardado, alNuevo,()->"No son iguales");
		
		
	}

	/**
	 * Test method for {@link com.jgr.micro.alumno.repository.IAlumnoRepository#buscaNombreOApellido(java.lang.String)}.
	 */
	@Test
	void testBuscaNombreOApellido() {
		Alumno al = new Alumno();
		al.setNombre("NOMBREDELNUEVOALUMNO");
		al.setApellidos("APELLIDODELNUEVOALUMNO");
		al.setEmail("emailnuevo@mail.com");
		al.setCreateAt(new Date());
		Alumno alGuardado=iAlumnoRepository.save(al);
				
		Alumno alNuevo  = ((List<Alumno>) iAlumnoRepository.buscaNombreOApellido(alGuardado.getApellidos())).get(0);
				

		assertEquals(alGuardado, alNuevo,()->"No son iguales");
		
	}

	/**
	 * Test method for {@link com.jgr.micro.alumno.repository.IAlumnoRepository#findByNombreContainingIgnoreCaseOrApellidosContainingIgnoreCase(java.lang.String, java.lang.String)}.
	 */
	@Test
	void testFindByNombreContainingIgnoreCaseOrApellidosContainingIgnoreCase() {
		
		Alumno al = new Alumno();
		al.setNombre("NOMBREDELNUEVOALUMNO");
		al.setApellidos("APELLIDODELNUEVOALUMNO");
		al.setEmail("emailnuevo@mail.com");
		al.setCreateAt(new Date());
		Alumno alGuardado=iAlumnoRepository.save(al);
				
		Alumno alNuevo  = ((List<Alumno>) iAlumnoRepository
				.findByNombreContainingIgnoreCaseOrApellidosContainingIgnoreCase(al.getNombre(),al.getApellidos())).get(0);

		assertEquals(alGuardado, alNuevo,()->"No son iguales");
	}


}
