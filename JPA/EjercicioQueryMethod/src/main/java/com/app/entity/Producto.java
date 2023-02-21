package com.app.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "productos")
public class Producto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "codigo")
	private String codigo;
	
	@Column(name = "vendedor")
	private String vendedor;
	
	@Column(name = "precio")
	private Integer precio;
	
	@Column(name = "activo")
	private Boolean activo;

	public Producto() {
		super();
	}

	public Producto(Integer id, String codigo, String vendedor, Integer precio, Boolean activo) {
		super();
		this.id = id;
		this.codigo = codigo;
		this.vendedor = vendedor;
		this.precio = precio;
		this.activo = activo;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getVendedor() {
		return vendedor;
	}

	public void setVendedor(String vendedor) {
		this.vendedor = vendedor;
	}

	public Integer getPrecio() {
		return precio;
	}

	public void setPrecio(Integer precio) {
		this.precio = precio;
	}

	public Boolean getActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

	@Override
	public String toString() {
		return "Producto [id=" + id + ", codigo=" + codigo + ", vendedor=" + vendedor + ", precio=" + precio
				+ ", activo=" + activo + "]";
	}

}
