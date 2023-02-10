package com.jgr.micro.alumno.test.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.jgr.micro.alumno.repository.IAlumnoRepository;
import com.jgr.micro.alumno.service.AlumnoServiceImpl;
import com.jgr.micro.alumno.service.IAlumnoService;


@ExtendWith(MockitoExtension.class)
class AlumnoServiceImplTest {
	
	@Mock
	IAlumnoRepository iAlumnoRepository;
	
	 @InjectMocks
	 AlumnoServiceImpl alumnoService;

	@BeforeEach
	void setUp() throws Exception {
		
		
	}

	@Test
	@DisplayName("testFindAll()")
	void testFindAll() {
		alumnoService.findAll();
	}

	
	/*
	@Test
	@DisplayName("testFindById()")
	void testFindById() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	@DisplayName("testSave()")
	void testSave() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	@DisplayName("testDeleteById()")
	void testDeleteById() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	@DisplayName("testFindByNombreLikeIgnoreCase()")
	void testFindByNombreLikeIgnoreCase() {
		fail("Not yet implemented"); // TODO
	}
*/
}
