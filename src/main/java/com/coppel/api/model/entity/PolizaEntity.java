package com.coppel.api.model.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Builder;

@Builder
@Entity
@Table(name = "polizas")
public class PolizaEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	public PolizaEntity() {

	}

	public PolizaEntity(Integer id, Integer empleadoGenero, Integer sku, Integer cantidad, Timestamp fecha) {
		super();
		this.id = id;
		this.empleadoGenero = empleadoGenero;
		this.sku = sku;
		this.cantidad = cantidad;
		this.fecha = fecha;
	}

	public PolizaEntity(Integer empleadoGenero, Integer sku, Integer cantidad, Timestamp fecha) {
		super();
		this.empleadoGenero = empleadoGenero;
		this.sku = sku;
		this.cantidad = cantidad;
		this.fecha = fecha;
	}

	// @GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idPoliza", nullable = false)
	private Integer id;
	
	@Column(name = "empleadoGenero")
	private Integer empleadoGenero;
	
	@Column(name = "sku")
	private Integer sku;
	
	@Column(name = "cantidad")
	private Integer cantidad;
	
	@Column(name = "fecha")
	private java.sql.Timestamp fecha;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public java.sql.Timestamp getFecha() {
		return fecha;
	}

	public void setFecha(java.sql.Timestamp fecha) {
		this.fecha = fecha;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PolizaEntity [id=");
		builder.append(id);
		builder.append(", empleadoGenero=");
		builder.append(empleadoGenero);
		builder.append(", sku=");
		builder.append(sku);
		builder.append(", cantidad=");
		builder.append(cantidad);
		builder.append(", fecha=");
		builder.append(fecha);
		builder.append("]");
		return builder.toString();
	}

	/*
	 * @OneToOne(targetEntity = EmpleadoEntity.class) private EmpleadoEntity
	 * empleado;
	 * 
	 * @OneToOne(targetEntity = InventarioEntity.class) private InventarioEntity
	 * inventario;
	 */

}
