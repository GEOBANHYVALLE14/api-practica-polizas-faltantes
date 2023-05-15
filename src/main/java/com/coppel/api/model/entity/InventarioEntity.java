package com.coppel.api.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Entity
@Table(name = "inventario")
@Getter
@Setter
public class InventarioEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	
	// @GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "sku", nullable = false)
	private Integer id;

	@Column(name = "nombre", nullable = false, unique = true)
	private String nombre;

	@Column(name = "cantidad", nullable = false)
	private Integer cantidad;

	public InventarioEntity(Integer id, String nombre, Integer cantidad) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.cantidad = cantidad;
	}

	public InventarioEntity() {
	}

	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("InventarioEntity [id=");
		builder.append(id);
		builder.append(", nombre=");
		builder.append(nombre);
		builder.append(", cantidad=");
		builder.append(cantidad);
		builder.append("]");
		return builder.toString();
	}

}
