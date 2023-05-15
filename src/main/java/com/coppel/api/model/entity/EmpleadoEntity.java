package com.coppel.api.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.AllArgsConstructor;
import lombok.Builder;

@AllArgsConstructor
//@NoArgsConstructor
@Builder

@Entity
@Table(name = "empleado", uniqueConstraints = { @UniqueConstraint(columnNames = { "nombre", "apellido" }) })
public class EmpleadoEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	// @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "idEmpleado", nullable = false)
	private Integer idEmpleado;
	@Column(name = "nombre", nullable = false)
	private String nombre;
	@Column(name = "apellido", nullable = false)
	private String apellido;
	@Column(name = "puesto", nullable = false)
	private String puesto;

	public EmpleadoEntity() {

	}

	public Integer getIdEmpleado() {
		return idEmpleado;
	}

	public void setIdEmpleado(Integer idEmpleado) {
		this.idEmpleado = idEmpleado;
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

	public String getPuesto() {
		return puesto;
	}

	public void setPuesto(String puesto) {
		this.puesto = puesto;
	}

	@Override
	public String toString() {
		return "EmpleadoEntity [idEmpleado=" + idEmpleado + ", nombre=" + nombre + ", apellido=" + apellido
				+ ", puesto=" + puesto + "]";
	}

}
