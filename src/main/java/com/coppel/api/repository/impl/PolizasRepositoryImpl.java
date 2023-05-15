package com.coppel.api.repository.impl;

import java.sql.Timestamp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.coppel.api.repository.PolizaRepository;

@Repository
public class PolizasRepositoryImpl implements PolizaRepository {
	private static final Logger logger = LoggerFactory.getLogger(PolizasRepositoryImpl.class);
	private JdbcTemplate jdbcTemplate;

	@Autowired
	public PolizasRepositoryImpl(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public int grabarPoliza(Integer empleadoGenero, Integer sku, int cantidad, Timestamp fechaTimestamp) {
		Integer result = 0;
		String CONSULTA_FUN_GRABAR_POLIZA = String.format(
				"SELECT fun_grabar_poliza AS iResp FROM fun_grabar_poliza(%s, %s, %s, '%s')", empleadoGenero, sku,
				cantidad, fechaTimestamp);

		logger.info("grabarPoliza SQL: ".concat(CONSULTA_FUN_GRABAR_POLIZA));

		try {

			// result = jdbcTemplate.update(CONSULTA_FUN_GRABAR_POLIZA, empleadoGenero, sku,
			// cantidad, fechaTimestamp);
			result = jdbcTemplate.queryForObject(CONSULTA_FUN_GRABAR_POLIZA, Integer.class);

		} catch (DataAccessException e) {
			logger.error("Exception: " + e);
			result = -1;
		}

		return (result != null ? result : 0);

	}

	@Override
	public int eliminarPoliza(Integer id) {
		Integer result = 0;
		String CONSULTA_FUN_ELIMINAR_POLIZA = String.format(
				"SELECT fun_eliminar_poliza AS iResp FROM fun_eliminar_poliza(%s)", id);

		logger.info("eliminarPoliza SQL: ".concat(CONSULTA_FUN_ELIMINAR_POLIZA));

		try {

			// result = jdbcTemplate.update(CONSULTA_FUN_GRABAR_POLIZA, empleadoGenero, sku,
			// cantidad, fechaTimestamp);
			result = jdbcTemplate.queryForObject(CONSULTA_FUN_ELIMINAR_POLIZA, Integer.class);

		} catch (DataAccessException e) {
			logger.error("Exception: " + e);
			result = -1;
		}

		return (result != null ? result : 0);
	}

}
