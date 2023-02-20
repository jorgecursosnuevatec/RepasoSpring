package com.jgr.servicio.curso.sql.entity;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;

import com.jgr.model.sql.alumno.entity.Alumno;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;



@Entity
@Getter
@Setter
@ToString
@Table(name="cursos")
public class Curso {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idCurso;
	
	@NotEmpty
	private String nombreCurso;
	
	@Column(name="create_at")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createAt;
	
	@OneToMany(fetch = FetchType.LAZY,orphanRemoval = true)
	private List<Alumno> alumnos;
	/*
	@ManyToMany(fetch = FetchType.LAZY)
	private List<Examen> examenes;
*/
	@PrePersist
	public void prePersist() {
		this.createAt = new Date();
	}

	public Curso() {
		this.alumnos = new ArrayList<>();
//		this.examenes = new ArrayList<>();
	}

	
	public void addAlumno(Alumno alumno) {
		this.alumnos.add(alumno);
	}

	public void removeAlumno(Alumno alumno) {
		this.alumnos.remove(alumno);
	}

	@Override
	public int hashCode() {
		return Objects.hash(idCurso);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Curso)) {
			return false;
		}
		Curso other = (Curso) obj;
		return Objects.equals(idCurso, other.idCurso);
	}

	/*
	public List<Examen> getExamenes() {
		return examenes;
	}

	public void setExamenes(List<Examen> examenes) {
		this.examenes = examenes;
	}

	public void addExamen(Examen examen) {
		this.examenes.add(examen);
	}
	
	public void removeExamen(Examen examen) {
		this.examenes.remove(examen);
	}
	*/
	
	
	
}
