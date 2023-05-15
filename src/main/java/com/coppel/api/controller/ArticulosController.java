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

import com.coppel.api.model.entity.InventarioEntity;

@RestController
public interface ArticulosController {
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public InventarioEntity guardarArticulo(@RequestBody InventarioEntity articulo);

	@GetMapping
	public List<InventarioEntity> listarArticulos();

	@GetMapping("/{id}")
	public ResponseEntity<InventarioEntity> obtenerArticuloPorId(@PathVariable("id") Integer articuloId);

	@PutMapping
	public ResponseEntity<InventarioEntity> actualizarArticulo(@RequestBody InventarioEntity articulo);

	@DeleteMapping("/{id}")
	public ResponseEntity<String> eliminarArticulo(@PathVariable("id") Integer articuloId);
}
