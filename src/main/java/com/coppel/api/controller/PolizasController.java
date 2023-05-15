package com.coppel.api.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.coppel.api.model.RequestGrabarPoliza;
import com.coppel.api.model.ResponseCoppel;
import com.coppel.api.model.entity.PolizaEntity;

@RestController
public interface PolizasController {
/*
	@PostMapping(value = "/polizas", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public abstract ResponseEntity<ResponseCoppel> grabarPoliza(@RequestBody RequestGrabarPoliza request);

	@GetMapping(value = "/polizas", produces = { MediaType.APPLICATION_JSON_VALUE })
	public abstract ResponseEntity<ResponseCoppel> mostrarPoliza(
			@RequestParam(name = "idPoliza", required = true) Integer id);

	@PutMapping(value = "/polizas", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public abstract ResponseEntity<ResponseCoppel> actualizarPoliza(@RequestBody RequestGrabarPoliza request);

	@DeleteMapping(value = "/polizas", produces = { MediaType.APPLICATION_JSON_VALUE })
	public abstract ResponseEntity<ResponseCoppel> eliminarPoliza(
			@RequestParam(name = "idPoliza", required = true) Integer id);

	@GetMapping(value = "/polizas", produces = { MediaType.APPLICATION_JSON_VALUE })
	public List<PolizaEntity> listarPolizas();

*/	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public abstract ResponseEntity<ResponseCoppel> grabarPoliza(@RequestBody RequestGrabarPoliza request);

	@GetMapping("/{id}")
	public abstract ResponseEntity<ResponseCoppel> mostrarPoliza(@PathVariable("id") Integer id);

	@PutMapping
	public abstract ResponseEntity<ResponseCoppel> actualizarPoliza(@RequestBody RequestGrabarPoliza request);

	@DeleteMapping("/{id}")
	public abstract ResponseEntity<ResponseCoppel> eliminarPoliza(@PathVariable("id") Integer id);

	@GetMapping
	public List<PolizaEntity> listarPolizas();
	

	
}
