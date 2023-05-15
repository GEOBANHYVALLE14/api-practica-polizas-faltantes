package com.coppel.api.model;

import lombok.Builder;

@Builder
public class RequestGrabarPoliza {
	private Integer idPoliza;
	private Integer empleadoGenero;
	private Integer sku;
	private int cantidad;

	public RequestGrabarPoliza(Integer idPoliza, Integer empleadoGenero, Integer sku, int cantidad) {
		super();
		this.idPoliza = idPoliza;
		this.empleadoGenero = empleadoGenero;
		this.sku = sku;
		this.cantidad = cantidad;
	}

	public RequestGrabarPoliza(Integer empleadoGenero, Integer sku, int cantidad) {
		super();

		this.empleadoGenero = empleadoGenero;
		this.sku = sku;
		this.cantidad = cantidad;
	}

	public RequestGrabarPoliza() {

	}

	public Integer getIdPoliza() {
		return idPoliza;
	}

	public void setIdPoliza(Integer idPoliza) {
		this.idPoliza = idPoliza;
	}

	public Integer getEmpleadoGenero() {
		return empleadoGenero;
	}

	public void setEmpleadoGenero(Integer empleadoGenero) {
		this.empleadoGenero = empleadoGenero;
	}

	public Integer getSku() {
		return sku;
	}

	public void setSku(Integer sku) {
		this.sku = sku;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RequestGrabarPoliza [idPoliza=");
		builder.append(idPoliza);
		builder.append(", empleadoGenero=");
		builder.append(empleadoGenero);
		builder.append(", sku=");
		builder.append(sku);
		builder.append(", cantidad=");
		builder.append(cantidad);
		builder.append("]");
		return builder.toString();
	}

}
