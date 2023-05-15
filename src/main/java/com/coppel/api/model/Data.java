package com.coppel.api.model;

import com.fasterxml.jackson.annotation.JsonInclude;

public class Data {

	public Data() {
		// TODO Auto-generated constructor stub
	}

	public Data(String token) {
		this.token = token;
	}

	public Data(String mensaje, String token) {
		this.mensaje = mensaje;
		// this.poliza = poliza;
	}

	public Data(Poliza poliza, Empleado empleado, Articulo detalleArticulo) {
		super();
		this.poliza = poliza;
		this.empleado = empleado;
		this.detalleArticulo = detalleArticulo;
	}

	@JsonInclude(value = JsonInclude.Include.NON_NULL)
	private String mensaje;
	@JsonInclude(value = JsonInclude.Include.NON_NULL)
	private Poliza poliza;
	@JsonInclude(value = JsonInclude.Include.NON_NULL)
	private Empleado empleado;
	@JsonInclude(value = JsonInclude.Include.NON_NULL)
	private Articulo detalleArticulo;
	@JsonInclude(value = JsonInclude.Include.NON_NULL)
	private String token;

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public Poliza getPoliza() {
		return poliza;
	}

	public void setPoliza(Poliza poliza) {
		this.poliza = poliza;
	}

	public Empleado getEmpleado() {
		return empleado;
	}

	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}

	public Articulo getDetalleArticulo() {
		return detalleArticulo;
	}

	public void setDetalleArticulo(Articulo detalleArticulo) {
		this.detalleArticulo = detalleArticulo;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Data [mensaje=");
		builder.append(mensaje);
		builder.append(", poliza=");
		builder.append(poliza);
		builder.append(", empleado=");
		builder.append(empleado);
		builder.append(", detalleArticulo=");
		builder.append(detalleArticulo);
		builder.append(", token=");
		builder.append(token);
		builder.append("]");
		return builder.toString();
	}

}
