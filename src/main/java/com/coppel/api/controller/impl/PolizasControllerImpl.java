package com.coppel.api.controller.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coppel.api.controller.PolizasController;
import com.coppel.api.model.Data;
import com.coppel.api.model.Meta;
import com.coppel.api.model.RequestGrabarPoliza;
import com.coppel.api.model.ResponseCoppel;
import com.coppel.api.model.entity.PolizaEntity;
import com.coppel.api.service.PolizasService;
import com.coppel.api.utils.Utilidades;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@CrossOrigin
@RestController
@RequestMapping("/api/polizas")
public class PolizasControllerImpl implements PolizasController {
	// private static final Logger logger =
	// LoggerFactory.getLogger(PolizasControllerImpl.class);
	private static Logger logger = LoggerFactory.getLogger(PolizasControllerImpl.class);

	// private static final org.owasp.html.PolicyFactory policy =
	// Sanitizers.FORMATTING.and(Sanitizers.LINKS);

	private PolizasService polizasServiceImpl;

	@Autowired
	public PolizasControllerImpl(PolizasService polizasServiceImpl) {
		this.polizasServiceImpl = polizasServiceImpl;
	}

	@Override
	public ResponseEntity<ResponseCoppel> grabarPoliza(RequestGrabarPoliza request) {
		logger.info("*************************************************** INICIO: grabarPoliza");

		ResponseEntity<ResponseCoppel> respuesta = null;
		String requestJson = null;
		ResponseCoppel responseCoppel = new ResponseCoppel();
		RequestGrabarPoliza requestSanitized = null;
		try {
			requestJson = new ObjectMapper().writeValueAsString(request);
			logger.info("request: {}", requestJson);

			requestJson = Utilidades.sanitizeInputAndOutput(requestJson);
			logger.info("request sanitized: {}", requestJson);

			requestSanitized = new ObjectMapper().readValue(requestJson, RequestGrabarPoliza.class);

			responseCoppel = polizasServiceImpl.grabarPoliza(requestSanitized);

			respuesta = new ResponseEntity<>(responseCoppel, HttpStatus.OK);

		} catch (JsonProcessingException e) {
			logger.info("Exception: {}", e.getMessage());

			respuesta = new ResponseEntity<>(new ResponseCoppel(new Meta("FAILURE"),
					new Data("Ha ocurrido un error en los grabados de póliza.", "")), HttpStatus.OK);

		} finally {
			logger.info("Response: {}", respuesta.getBody().toString());

			requestJson = null;
			responseCoppel = new ResponseCoppel();
			requestSanitized = null;
			logger.info("*************************************************** FIN: grabarPoliza");
		}
		return respuesta;
	}

	@Override
	public ResponseEntity<ResponseCoppel> mostrarPoliza(Integer id) {
		logger.info("*************************************************** INICIO: mostrarPoliza");

		ResponseCoppel responseCoppel = polizasServiceImpl.mostrarPoliza(id);

		ResponseEntity<ResponseCoppel> respuesta = new ResponseEntity<>(responseCoppel, HttpStatus.OK);

		logger.info("Response: {}", respuesta.getBody().toString());
		logger.info("*************************************************** FIN: mostrarPoliza");
		return respuesta;

	}

	@Override
	public ResponseEntity<ResponseCoppel> actualizarPoliza(RequestGrabarPoliza request) {
		logger.info("*************************************************** INICIO: actualizarPoliza");

		ResponseEntity<ResponseCoppel> respuesta = null;
		String requestJson = null;
		ResponseCoppel responseCoppel = new ResponseCoppel();
		RequestGrabarPoliza requestSanitized = null;
		try {
			requestJson = new ObjectMapper().writeValueAsString(request);
			logger.info("request: {}", requestJson);

			requestJson = Utilidades.sanitizeInputAndOutput(requestJson);
			logger.info("request sanitized: {}", requestJson);

			requestSanitized = new ObjectMapper().readValue(requestJson, RequestGrabarPoliza.class);

			responseCoppel = polizasServiceImpl.actualizarPoliza(requestSanitized);

			respuesta = new ResponseEntity<>(responseCoppel, HttpStatus.OK);

		} catch (JsonProcessingException e) {
			logger.info("Exception: {}", e.getMessage());

			respuesta = new ResponseEntity<>(new ResponseCoppel(new Meta("FAILURE"),
					new Data("Ha ocurrido un error al intentar actualizar la póliza.", "")), HttpStatus.OK);

		} finally {
			logger.info("Response: {}", respuesta.getBody().toString());

			requestJson = null;
			responseCoppel = new ResponseCoppel();
			requestSanitized = null;
			logger.info("*************************************************** FIN: actualizarPoliza");
		}
		return respuesta;
	}

	@Override
	public ResponseEntity<ResponseCoppel> eliminarPoliza(Integer id) {

		logger.info("*************************************************** INICIO: eliminarPoliza");

		ResponseCoppel responseCoppel = polizasServiceImpl.eliminarPoliza(id);

		ResponseEntity<ResponseCoppel> respuesta = new ResponseEntity<>(responseCoppel, HttpStatus.OK);

		logger.info("Response: {}", respuesta.getBody().toString());
		logger.info("*************************************************** FIN: eliminarPoliza");
		return respuesta;
	}

	@Override
	public List<PolizaEntity> listarPolizas() {
		return polizasServiceImpl.getAllPolizas();
	}

}
