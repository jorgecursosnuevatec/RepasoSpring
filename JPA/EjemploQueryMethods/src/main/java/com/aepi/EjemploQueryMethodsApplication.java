package com.aepi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.aepi.entity.Persona;
import com.aepi.repository.PersonaRepository;

@SpringBootApplication
public class EjemploQueryMethodsApplication implements CommandLineRunner {
	
	@Autowired
	private PersonaRepository personaRepository;
	
	private void cargaDeDatos() {
		personaRepository.save(new Persona(1, "Perico", "Garcia", 10, false, 100));
		personaRepository.save(new Persona(2, "Pedro", "Garcia", 20, true, 500));
		personaRepository.save(new Persona(3, "Juan", "Garcia", 30, true, 1500));
		personaRepository.save(new Persona(4, "Miguel", "Garcia", 40, true, 2000));
		personaRepository.save(new Persona(5, "Marta", "Garcia", 58, true, 2500));
		personaRepository.save(new Persona(6, "Marta", "Perez", 20, true, 2500));
		personaRepository.save(new Persona(7, "Marta", "Jimenez", 39, true, 2500));
		personaRepository.save(new Persona(8, "Sandra", "Garcia", 60, true, 800));
		personaRepository.save(new Persona(9, "Sergio", "Garcia", 54, true, 3000));
		personaRepository.save(new Persona(10, "Arancha", "Garcia", 26, true, 3500));
	}

	public static void main(String[] args) {
		SpringApplication.run(EjemploQueryMethodsApplication.class, args);
		
	}

	@Override
	public void run(String... args) throws Exception {
		cargaDeDatos();
	}

}
