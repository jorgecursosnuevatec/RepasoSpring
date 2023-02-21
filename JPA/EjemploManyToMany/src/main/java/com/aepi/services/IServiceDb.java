package com.aepi.services;

import java.util.List;

import com.aepi.entities.Alumno;
import com.aepi.entities.Clase;

public interface IServiceDb {

	public List<Alumno> findAllAlumnos();
	public void saveAlumno(Alumno alumno);
	public Alumno findByIdAlumno(Integer id);
	public void deleteAlumno(Alumno alumno);
	public List<Clase> findAllClases();
	public Clase findByIdClase(Integer id);
	public void saveClase(Clase clase);
	public void deleteClase(Clase clase);

}
