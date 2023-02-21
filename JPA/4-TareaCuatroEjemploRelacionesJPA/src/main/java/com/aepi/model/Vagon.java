package com.aepi.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


@Entity
@Table(name ="vagones")
public class Vagon {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	
	@Column(length = 50)
	@Size(max=50)
	@NotEmpty
	String categoria;
	
	@OneToMany(mappedBy="vagon")
	Set<Asiento> asientos = new HashSet<>();
	
	@ManyToOne
	@JoinColumn(name = "tren_id")
	Tren tren;

	public Vagon() {
	}
	
	public Vagon(Integer id, @Size(max=50) String categoria) {
		super();
		this.id = id;
		this.categoria = categoria;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public Set<Asiento> getAsientos() {
		return asientos;
	}

	public void addAsiento(Asiento asiento)
	{
		this.asientos.add(asiento);
	}
	
	public Tren getTren() {
		return tren;
	}

	public void setAsientos(Set<Asiento> asientos) {
		this.asientos = asientos;
	}

	public void setTren(Tren tren) {
		this.tren = tren;
	}


}
