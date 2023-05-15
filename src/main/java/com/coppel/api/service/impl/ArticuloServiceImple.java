package com.coppel.api.service.impl;

import java.util.List;
import java.util.Optional;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coppel.api.model.entity.InventarioEntity;
import com.coppel.api.repository.InventarioRepository;
import com.coppel.api.service.ArticuloService;

@Service
public class ArticuloServiceImple implements ArticuloService {

	@Autowired
	private InventarioRepository inventarioRepository;

	@Transactional
	@Override
	public InventarioEntity saveArticulo(InventarioEntity articulo) {
		return inventarioRepository.save(articulo);
	}

	@Transactional(readOnly = true)
	@Override
	public List<InventarioEntity> getAllArticulos() {
		return inventarioRepository.findAll();
	}

	@Transactional(readOnly = true)
	@Override
	public Optional<InventarioEntity> getArticuloById(Integer id) {
		return inventarioRepository.findById(id);
	}

	@Transactional
	@Override
	public InventarioEntity updateEmpleado(InventarioEntity articulo) {
		return inventarioRepository.save(articulo);
	}

	@Transactional
	@Override
	public void deleteEmpleado(Integer id) {
		inventarioRepository.deleteById(id);

	}

}
