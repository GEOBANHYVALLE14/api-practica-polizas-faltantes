package com.coppel.api.controller.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coppel.api.controller.EmpleadosController;
import com.coppel.api.model.entity.EmpleadoEntity;
import com.coppel.api.service.EmpleadoService;

@CrossOrigin
@RestController
@RequestMapping("/api/empleados")
public class EmpleadosControllerImpl implements EmpleadosController {
	@Autowired
	private EmpleadoService empleadoService;

	@Override
	public EmpleadoEntity guardarEmpleado(EmpleadoEntity empleado) {
		return empleadoService.saveEmpleado(empleado);
	}

	@Override
	public List<EmpleadoEntity> listarEmpleados() {
		return empleadoService.getAllEmpleados();
	}

	@Override
	public ResponseEntity<EmpleadoEntity> obtenerEmpleadoPorId(Integer empleadoId) {
		return empleadoService.getEmpleadoById(empleadoId).map(ResponseEntity::ok)
				.orElseGet(() -> ResponseEntity.notFound().build());
	}

	@Override
	public ResponseEntity<EmpleadoEntity> actualizarEmpleado(EmpleadoEntity empleado) {
		EmpleadoEntity empleadoActualizado;
		Optional<EmpleadoEntity> empleadoBD = empleadoService.getEmpleadoById(empleado.getIdEmpleado());
		if (empleadoBD.isPresent()) {
			empleadoActualizado = empleadoService.updateEmpleado(empleado);
			return new ResponseEntity<>(empleadoActualizado, HttpStatus.OK);
		} else {
			return ResponseEntity.notFound().build();

		}
	}

	@Override
	public ResponseEntity<String> eliminarEmpleado(Integer empleadoId) {
		empleadoService.deleteEmpleado(empleadoId);
		return new ResponseEntity<String>("Empleado eliminado exitosamente", HttpStatus.OK);

	}

}
