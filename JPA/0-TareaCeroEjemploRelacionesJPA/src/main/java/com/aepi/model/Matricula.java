package com.aepi.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "matriculas")
public class Matricula {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;

	String curso_academico;

	public Matricula() {
	}

	public Matricula(Integer id, String curso_academico) {
		super();
		this.id = id;
		this.curso_academico = curso_academico;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCurso_academico() {
		return curso_academico;
	}

	public void setCurso_academico(String curso_academico) {
		this.curso_academico = curso_academico;
	}

}
