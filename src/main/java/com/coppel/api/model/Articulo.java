package com.coppel.api.model;

public class Articulo {
	private Integer sku;
	private String nombre;

	public Articulo() {

	}

	public Articulo(Integer sku, String nombre) {
		super();
		this.sku = sku;
		this.nombre = nombre;
	}

	public Integer getSku() {
		return sku;
	}

	public void setSku(Integer sku) {
		this.sku = sku;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return "Articulo [sku=" + sku + ", nombre=" + nombre + "]";
	}
}
