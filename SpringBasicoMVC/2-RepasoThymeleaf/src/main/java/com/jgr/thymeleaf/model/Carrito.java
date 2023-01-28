package com.jgr.thymeleaf.model;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class Carrito {

	private int id;

	private String cliente;

	private List<String> productos;

	public Carrito(Carrito carrito) {
		this.id = carrito.id;
		this.cliente = carrito.cliente;
		this.productos = new ArrayList<String>(); 
		this.productos.addAll(carrito.productos);
	}

	public Carrito() {
		this.id = (int)(Math.random()*100);
		this.productos = new ArrayList<String>();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public List<String> getProductos() {
		return productos;
	}

	public void setProductos(List<String> productos) {
		this.productos = productos;
	}


	public void addProducto(String nuevoProducto) {
		
		productos.add(nuevoProducto);
		
	}

}
