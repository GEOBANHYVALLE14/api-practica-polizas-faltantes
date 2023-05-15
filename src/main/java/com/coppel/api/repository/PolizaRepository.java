package com.coppel.api.repository;

import java.sql.Timestamp;

public interface PolizaRepository {

	// PolizaEntity grabarPoliza(PolizaEntity entity);
	//public static final String CONSULTA_FUN_GRABAR_POLIZA = "SELECT fun_grabar_poliza AS iResp FROM fun_grabar_poliza(?,?,?,?)";

	public int grabarPoliza(Integer empleadoGenero, Integer sku, int cantidad, Timestamp fechaTimestamp);

	public int eliminarPoliza(Integer id);

}
