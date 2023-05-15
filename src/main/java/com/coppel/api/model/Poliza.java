package com.coppel.api.model;

public class Poliza {
	private Integer idPoliza;
	private int cantidad;

	public Poliza() {

	}

	public Poliza(Integer idPoliza, int cantidad) {
		super();
		this.idPoliza = idPoliza;
		this.cantidad = cantidad;
	}

	public int getIdPoliza() {
		return idPoliza;
	}

	public void setIdPoliza(Integer idPoliza) {
		this.idPoliza = idPoliza;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	@Override
	public String toString() {
		return "Poliza [idPoliza=" + idPoliza + ", cantidad=" + cantidad + "]";
	}

}
