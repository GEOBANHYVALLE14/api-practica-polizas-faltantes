package com.coppel.api.service;

import java.util.List;
import java.util.Optional;

import com.coppel.api.model.entity.EmpleadoEntity;

public interface EmpleadoService {

	EmpleadoEntity saveEmpleado(EmpleadoEntity empleado);

	List<EmpleadoEntity> getAllEmpleados();

	Optional<EmpleadoEntity> getEmpleadoById(Integer id);

	EmpleadoEntity updateEmpleado(EmpleadoEntity empleadoActualizado);

	void deleteEmpleado(Integer id);
}
