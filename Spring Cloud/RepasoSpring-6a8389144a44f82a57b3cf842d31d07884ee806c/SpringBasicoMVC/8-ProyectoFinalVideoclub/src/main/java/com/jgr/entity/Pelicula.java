package com.jgr.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "peliculas")
public class Pelicula {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "tituloPelicula")
	@NotEmpty
	private String tituloPelicula;
	
	@Column(name = "sinopsisPelicula")
	@NotEmpty
	@Lob
	private String sinopsisPelicula;
		
	@Column(name = "fechaEstrenoPelicula")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@NotNull
	private Date fechaEstrenoPelicula;
	
	@Column(name = "urlPelicula")
	@NotEmpty
	private String urlPelicula;
	
	@Lob //ESTO INDICA QUE VA A SER UN ARCHIVO GRANDE,COMO BLOB
	@Column(name = "portadaPelicula")
	//@NotNull
	private String portadaPelicula;
	
	
	@ManyToOne(fetch = FetchType.EAGER)
	private Genero genero;

	public Pelicula() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTituloPelicula() {
		return tituloPelicula;
	}

	public void setTituloPelicula(String tituloPelicula) {
		this.tituloPelicula = tituloPelicula;
	}

	public String getSinopsisPelicula() {
		return sinopsisPelicula;
	}

	public void setSinopsisPelicula(String sinopsisPelicula) {
		this.sinopsisPelicula = sinopsisPelicula;
	}

	public Date getFechaEstrenoPelicula() {
		return fechaEstrenoPelicula;
	}

	public void setFechaEstrenoPelicula(Date fechaEstrenoPelicula) {
		this.fechaEstrenoPelicula = fechaEstrenoPelicula;
	}

	public String getUrlPelicula() {
		return urlPelicula;
	}

	public void setUrlPelicula(String urlPelicula) {
		this.urlPelicula = urlPelicula;
	}

	public String getPortadaPelicula() {
		return portadaPelicula;
	}

	public void setPortadaPelicula(String portadaPelicula) {
		this.portadaPelicula = portadaPelicula;
	}

	public Genero getGenero() {
		return genero;
	}

	public void setGenero(Genero genero) {
		this.genero = genero;
	}

	@Override
	public String toString() {
		return "Pelicula [id=" + id + ", tituloPelicula=" + tituloPelicula + ", sinopsisPelicula=" + sinopsisPelicula
				+ ", fechaEstrenoPelicula=" + fechaEstrenoPelicula + ", urlPelicula=" + urlPelicula
				+ ", portadaPelicula=" + portadaPelicula + "]";
	}

	
	
	
}
