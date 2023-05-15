package com.coppel.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.coppel.api.model.entity.InventarioEntity;

@Repository
public interface InventarioRepository extends JpaRepository<InventarioEntity, Integer> {

	static final String CONSULTA_1 = "SELECT sku, nombre, cantidad FROM inventario WHERE sku= :sku";
	static final String CONSULTA_2 = "DELETE FROM inventario WHERE sku= :sku";

	@Query(value = CONSULTA_1, nativeQuery = true)
	Optional<InventarioEntity> findBySku(Integer sku);

	@Query(value = CONSULTA_2, nativeQuery = true)
	void deleteBySku(Integer sku);

}
