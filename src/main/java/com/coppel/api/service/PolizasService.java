package com.coppel.api.service;

import java.util.List;

import com.coppel.api.model.RequestGrabarPoliza;
import com.coppel.api.model.ResponseCoppel;
import com.coppel.api.model.entity.PolizaEntity;

public interface PolizasService {

	public ResponseCoppel grabarPoliza(RequestGrabarPoliza request);

	public ResponseCoppel mostrarPoliza(Integer id);

	public ResponseCoppel actualizarPoliza(RequestGrabarPoliza requestSanitized);

	public ResponseCoppel eliminarPoliza(Integer id);

	public List<PolizaEntity> getAllPolizas();

}
