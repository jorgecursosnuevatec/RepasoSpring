package com.aepi.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "clases")
public class Clase {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Column(name = "nombre")
	private String nombre;

	@Column(name = "edificio")
	private String edificio;

	@Column(name = "materia")
	private String materia;

	@Column(name = "aforo")
	private Integer aforo;

	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "clase_alumno", joinColumns = @JoinColumn(name = "clase_id"), inverseJoinColumns = @JoinColumn(name = "alumno_id"))
	private Set<Alumno> alumnos = new HashSet<>();

	public Clase() {

	}

	public Clase(String nombre, String edificio, String materia, Integer aforo) {
		super();
		this.nombre = nombre;
		this.edificio = edificio;
		this.materia = materia;
		this.aforo = aforo;
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

	public String getEdificio() {
		return edificio;
	}

	public void setEdificio(String edificio) {
		this.edificio = edificio;
	}

	public String getMateria() {
		return materia;
	}

	public void setMateria(String materia) {
		this.materia = materia;
	}

	public Integer getAforo() {
		return aforo;
	}

	public void setAforo(Integer aforo) {
		this.aforo = aforo;
	}

	public Set<Alumno> getAlumnos() {
		return alumnos;
	}

	public void setAlumnos(Set<Alumno> alumnos) {
		this.alumnos = alumnos;
	}

	public void addAlumno(Alumno alumno) {
		alumnos.add(alumno);
		alumno.getClases().add(this);
	}

	public void deleteAlumno(Alumno alumno) {
		alumnos.remove(alumno);
		alumno.getClases().remove(this);
	}
}
