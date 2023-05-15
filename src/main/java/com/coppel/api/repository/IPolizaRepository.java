package com.coppel.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.coppel.api.model.entity.PolizaEntity;

@Repository
public interface IPolizaRepository extends JpaRepository<PolizaEntity, Integer> {

	static final String CONSULTA_1 = "SELECT * FROM polizas WHERE idPoliza = :idPoliza";
	static final String CONSULTA_2 = "DELETE FROM polizas p WHERE p.idPoliza = :idPoliza";

	@Query(value = CONSULTA_1, nativeQuery = true)
	Optional<PolizaEntity> findByIdPoliza(@Param("idPoliza") Integer idPoliza);

	@Query(value = CONSULTA_2, nativeQuery = true)
	Optional<PolizaEntity> deleteByIdPoliza(@Param("idPoliza") Integer idPoliza);

}
