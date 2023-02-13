/*
 * 
 */
package com.jgr.micro.alumno.test.controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;


/**
 * The Class AlumnoControllerRestTemplateTest.
 * 
 * ordenamos porque necesitamos que se ejecute en orden
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment=SpringBootTest.WebEnvironment.RANDOM_PORT)

class AlumnoControllerRestTemplateTest {
	
	@Autowired
	private TestRestTemplate testRestTemplate;
	
	private ObjectMapper objectMapper;

	/**
	 * Sets the up.
	 *
	 * @throws Exception the exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		
		objectMapper = new ObjectMapper();
	}

	/**
	 * Test.
	 */
	@Test
	void test() {
		fail("Not yet implemented"); // TODO
	}

}
