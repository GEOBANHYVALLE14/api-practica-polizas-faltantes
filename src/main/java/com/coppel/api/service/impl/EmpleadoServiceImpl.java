package com.coppel.api.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coppel.api.model.entity.EmpleadoEntity;
import com.coppel.api.repository.EmpleadoRepository;
import com.coppel.api.service.EmpleadoService;

@Service
public class EmpleadoServiceImpl implements EmpleadoService {
	@Autowired
	private EmpleadoRepository empleadoRepository;

	@Override
	public EmpleadoEntity saveEmpleado(EmpleadoEntity empleado) {
		/*Optional<EmpleadoEntity> empleadoGuardado = empleadoRepository.findById(empleado.getIdEmpleado());
		if (empleadoGuardado.isPresent()) {
			throw new ResourceNotFoundException("El empleado con ese email ya existe : " + empleado.getEmail());
		}*/
		return empleadoRepository.save(empleado);
	}

	@Override
	public List<EmpleadoEntity> getAllEmpleados() {
		return empleadoRepository.findAll();
	}

	@Override
	public Optional<EmpleadoEntity> getEmpleadoById(Integer id) {
		return empleadoRepository.findById(id);
	}

	@Override
	public EmpleadoEntity updateEmpleado(EmpleadoEntity empleadoActualizado) {
		return empleadoRepository.save(empleadoActualizado);
	}

	@Override
	public void deleteEmpleado(Integer id) {
		empleadoRepository.deleteById(id);

	}

}
