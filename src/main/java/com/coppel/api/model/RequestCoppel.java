package com.coppel.api.model;

public class RequestCoppel {
	private Poliza poliza;
	private Empleado empleado;
	private Articulo detalleArticulo;

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

	@Override
	public String toString() {
		return "RequestCoppel [poliza=" + poliza + ", empleado=" + empleado + ", detalleArticulo=" + detalleArticulo
				+ "]";
	}

}
