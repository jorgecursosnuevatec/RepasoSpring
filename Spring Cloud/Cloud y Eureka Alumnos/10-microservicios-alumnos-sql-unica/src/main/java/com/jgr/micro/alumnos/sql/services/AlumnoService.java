package com.jgr.micro.alumnos.sql.services;

import java.util.List;

import com.jgr.commons.modelo.alumnos.Alumno;
import com.jgr.commons.service.CommonService;

public interface AlumnoService extends CommonService<Alumno>{
	
	public List<Alumno> findByNombreOrApellidoIgnoringCase(String term);
}
