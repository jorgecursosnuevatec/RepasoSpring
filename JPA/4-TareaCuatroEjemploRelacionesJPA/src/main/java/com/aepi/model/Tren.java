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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "trenes")
public class Tren {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;

	@Size(max = 10)
	@NotEmpty
	String matricula;

	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "tren_viajero", joinColumns = @JoinColumn(name = "tren_id"), inverseJoinColumns = @JoinColumn(name = "viajero_id"))
	private Set<Viajero> viajeros = new HashSet<Viajero>();
	
	@OneToMany(mappedBy="tren")
	Set<Vagon> vagones = new HashSet<Vagon>();

	public Tren() {
	}

	public Tren(Integer id, @Size(max = 10) String matricula) {
		super();
		this.id = id;
		this.matricula = matricula;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public Set<Viajero> getViajeros() {
		return viajeros;
	}

	public void setViajeros(Set<Viajero> viajeros) {
		this.viajeros = viajeros;
	}

	public void addViajero(Viajero viajero)
	{
		this.viajeros.add(viajero);
	}

	
	public void setVagones(Set<Vagon> vagones) {
		this.vagones = vagones;
	}

	public Set<Vagon> getVagones() {
		return vagones;
	}

	public void addVagon(@Valid Vagon vagon) {
		this.vagones.add(vagon);		
	}
	
}
