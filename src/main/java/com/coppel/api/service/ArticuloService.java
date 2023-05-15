package com.coppel.api.service;

import java.util.List;
import java.util.Optional;

import com.coppel.api.model.entity.InventarioEntity;

public interface ArticuloService {
	InventarioEntity saveArticulo(InventarioEntity articulo);

	List<InventarioEntity> getAllArticulos();

	Optional<InventarioEntity> getArticuloById(Integer id);

	InventarioEntity updateEmpleado(InventarioEntity articulo);

	void deleteEmpleado(Integer id);
}
