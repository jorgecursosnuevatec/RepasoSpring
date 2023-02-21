package com.aepi.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name ="asignaturas")
public class Asignatura {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;	

	String nombre;	

	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "asignatura_estudiante", 
				joinColumns = @JoinColumn(name = "asignatura_id"), 
				inverseJoinColumns = @JoinColumn(name = "estudiante_id"))
	private Set<Estudiante> estudiantes = new HashSet<>();

	public Asignatura() {
	}

	public Asignatura(Integer id, String nombre) {
		super();
		this.id = id;
		this.nombre = nombre;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void addEstudiante(Estudiante estu) {
		this.estudiantes.add(estu);
		
	}	
}
