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

import com.coppel.api.model.entity.EmpleadoEntity;

@RestController
public interface EmpleadosController {

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public EmpleadoEntity guardarEmpleado(@RequestBody EmpleadoEntity empleado);

	@GetMapping
	public List<EmpleadoEntity> listarEmpleados();

	@GetMapping("/{id}")
	public ResponseEntity<EmpleadoEntity> obtenerEmpleadoPorId(@PathVariable("id") Integer empleadoId);

	@PutMapping
	public ResponseEntity<EmpleadoEntity> actualizarEmpleado(@RequestBody EmpleadoEntity empleado);

	@DeleteMapping("/{id}")
	public ResponseEntity<String> eliminarEmpleado(@PathVariable("id") Integer empleadoId);
}
