package com.aepi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aepi.entities.Alumno;
import com.aepi.entities.Clase;
import com.aepi.repository.AlumnoRepository;
import com.aepi.repository.ClaseRepository;

@Service
public class ServiceDbImpl implements IServiceDb {

	@Autowired
	private AlumnoRepository alumnoRepository;

	@Autowired
	private ClaseRepository claseRepository;

	@Override
	public List<Alumno> findAllAlumnos() {
		return alumnoRepository.findAll();
	}

	@Override
	public void saveAlumno(Alumno alumno) {
		alumnoRepository.save(alumno);
	}

	@Override
	public Alumno findByIdAlumno(Integer id) {
		return alumnoRepository.findById(id).get();
	}

	@Override
	public void deleteAlumno(Alumno alumno) {
		alumnoRepository.delete(alumno);
	}

	@Override
	public List<Clase> findAllClases() {
		return claseRepository.findAll();
	}

	@Override
	public Clase findByIdClase(Integer id) {
		return claseRepository.findById(id).get();
	}

	@Override
	public void saveClase(Clase clase) {
		claseRepository.save(clase);
	}

	@Override
	public void deleteClase(Clase clase) {
		claseRepository.delete(clase);
	}

}
