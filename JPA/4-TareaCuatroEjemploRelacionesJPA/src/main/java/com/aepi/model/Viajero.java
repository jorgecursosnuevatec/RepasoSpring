package com.aepi.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


@Entity
@Table(name ="viajeros")
public class Viajero {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	
	@Column(length = 50)
	@Size(max=50)
	@NotEmpty
	String nombre;
	
	@Column(length = 50)
	@Size(max=50)
	@NotEmpty
	String apellidos;
	
	@Column(length = 9)
	@Size(min=9,max=9)
	@NotEmpty
	String dni;
	
	@ManyToMany(mappedBy = "viajeros")
	private Set<Tren> trenes = new HashSet<>();

	public Viajero() {
	}
	
	public Viajero(Integer id, String nombre, String apellidos, @Size(min = 9, max = 9) String dni) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.dni = dni;
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

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}
}
