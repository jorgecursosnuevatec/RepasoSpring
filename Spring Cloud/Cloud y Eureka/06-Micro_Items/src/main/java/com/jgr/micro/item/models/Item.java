package com.jgr.micro.item.models;


/**
 * The Class Item.
 * es el pedido que se realiza
 */
public class Item {

	/** The producto. */
	private Producto producto;
	
	/** The cantidad. */
	private Integer cantidad;

	/**
	 * Instantiates a new item.
	 */
	public Item() {
	}

	/**
	 * Instantiates a new item.
	 *
	 * @param producto the producto
	 * @param cantidad the cantidad
	 */
	public Item(Producto producto, Integer cantidad) {
		this.producto = producto;
		this.cantidad = cantidad;
	}

	/**
	 * Gets the producto.
	 *
	 * @return the producto
	 */
	public Producto getProducto() {
		return producto;
	}

	/**
	 * Sets the producto.
	 *
	 * @param producto the new producto
	 */
	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	/**
	 * Gets the cantidad.
	 *
	 * @return the cantidad
	 */
	public Integer getCantidad() {
		return cantidad;
	}

	/**
	 * Sets the cantidad.
	 *
	 * @param cantidad the new cantidad
	 */
	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}
	
	/**
	 * Gets the total.
	 *
	 * @return the total
	 */
	public Double getTotal() {
		return producto.getPrecio() * cantidad.doubleValue();
	}

}
