package com.jgr.micro.usuarios.sql.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jgr.commons.modelo.alumnos.Alumno;
import com.jgr.commons.service.CommonServiceImpl;
import com.jgr.micro.usuarios.sql.models.repository.AlumnoRepository;

@Service
public class AlumnoServiceImpl extends CommonServiceImpl<Alumno, AlumnoRepository> implements AlumnoService {

	@Override
	@Transactional(readOnly = true)
	public List<Alumno> findByNombreOrApellido(String term) {
		return repository.findByNombreOrApellido(term);
	}
	

}
