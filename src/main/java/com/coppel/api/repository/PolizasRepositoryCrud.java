package com.coppel.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.coppel.api.model.entity.PolizaEntity;

@Repository
public interface PolizasRepositoryCrud extends JpaRepository<PolizaEntity, Integer> {
	// static final String CONSULTA_1 = "SELECT idEmpleado, nombre, apellido, puesto
	// FROM empleado WHERE nombre = :nombre AND apellido = :apellido";

	// @Query(value = CONSULTA_1, nativeQuery = true)
	// Optional<Usuario> findOneByNameApellido(String nombre, String apellido);
}
