package com.coppel.api.model;

import com.fasterxml.jackson.annotation.JsonInclude;

public class Empleado {
	@JsonInclude(value = JsonInclude.Include.NON_NULL)
	private Integer id;
	@JsonInclude(value = JsonInclude.Include.NON_NULL)
	private String nombre;
	@JsonInclude(value = JsonInclude.Include.NON_NULL)
	private String apellido;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Empleado [id=");
		builder.append(id);
		builder.append(", nombre=");
		builder.append(nombre);
		builder.append(", apellido=");
		builder.append(apellido);
		builder.append("]");
		return builder.toString();
	}

}
