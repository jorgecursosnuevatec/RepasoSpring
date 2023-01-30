package com.jgr.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;


@Entity
@Table(name = "generos")
public class Genero {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "tipoGenero")
	@NotEmpty
	private String tipoGenero;
	
	@OneToMany(cascade = CascadeType.PERSIST, mappedBy = "genero", fetch = FetchType.LAZY)
	private Set <Pelicula> peliculas;

	public Genero() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTipoGenero() {
		return tipoGenero;
	}

	public void setTipoGenero(String tipoGenero) {
		this.tipoGenero = tipoGenero;
	}

	public Set<Pelicula> getPeliculas() {
		return peliculas;
	}

	public void setPeliculas(Set<Pelicula> peliculas) {
		this.peliculas = peliculas;
	}

	@Override
	public String toString() {
		return "Genero [id=" + id + ", tipoGenero=" + tipoGenero + ", peliculas=" + peliculas + "]";
	}

	
	
	
}
