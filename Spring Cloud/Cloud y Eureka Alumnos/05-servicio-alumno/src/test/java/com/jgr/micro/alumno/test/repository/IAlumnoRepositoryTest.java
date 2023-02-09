package com.jgr.micro.alumno.test.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
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
	@Autowired
	
	private IAlumnoRepository iAlumnoRepository;
	
	private static int limite=5;
	
	private  List<Alumno> alumnos;

	/**
	 * Sets the up.
	 * Solo una vez para todas las ejecuciones
	 *
	 * @throws Exception the exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		
		Alumno al;
		alumnos = new ArrayList<>();
		
		for (int i=0;i<limite;i++) {
			al = new Alumno();
			al.setNombre("Nombre"+i);
			al.setApellido("Apellido"+i);
			al.setEmail("Email"+i+"@mail.com");
			iAlumnoRepository.save(al);
			alumnos.add(al);
		}
		
	}

	/**
	 * Test find by nombre like ignore case.
	 */
	@Test
	void testFindByNombreLikeIgnoreCase() {
		
		Alumno al = alumnos.get(limite-1);
		//le cambio el nombre a un alumno,a ver si lo encuentra		
		String nombreCambiado = al.getNombre().toLowerCase();
		List<Alumno> alumnosBuscados =(List<Alumno>)iAlumnoRepository.findByNombreLikeIgnoreCase(nombreCambiado);		
		Alumno al2 = alumnosBuscados.get(0);		
		assertEquals(al,al2,()->"no ha encontrado al alumno");
		
	}

	/**
	 * Test save.
	 */
	@Test
	void testSave() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test find by id.
	 */
	@Test
	void testFindById() {
		
		List<Alumno> listaAlumnosTrabajo = new ArrayList<>();
		listaAlumnosTrabajo = iAlumnoRepository.findAll(Sort.by(Direction.ASC, "nombre"));
		Alumno al = listaAlumnosTrabajo.get(0);
		
		Optional<Alumno> al2 = iAlumnoRepository.findById(al.getIdAlumno());
		System.out.println("*********************alumno PRESENTE->"+al2.isPresent());
		System.out.println("\n*********************alumno GET->"+al2.get());
		
		assertTrue(al2.isPresent(),()->"El alumno NO existe");
		assertEquals(al2.get(),al,()->"No es igual que el recuperado de la BBDD");
		
	}

	/**
	 * Test delete by id.
	 */
	@Test
	void testDeleteById() {
		fail("Not yet implemented"); // TODO
	}

}
