package com.coppel.api.controller.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coppel.api.controller.ArticulosController;
import com.coppel.api.model.entity.InventarioEntity;
import com.coppel.api.service.ArticuloService;

@CrossOrigin
@RestController
@RequestMapping("/api/articulos")
public class ArticulosControllerImpl implements ArticulosController {
	@Autowired
	private ArticuloService articuloService;

	@Override
	public InventarioEntity guardarArticulo(InventarioEntity articulo) {
		return articuloService.saveArticulo(articulo);
	}

	@Override
	public List<InventarioEntity> listarArticulos() {
		return articuloService.getAllArticulos();
	}

	@Override
	public ResponseEntity<InventarioEntity> obtenerArticuloPorId(Integer articuloId) {
		return articuloService.getArticuloById(articuloId).map(ResponseEntity::ok)
				.orElseGet(() -> ResponseEntity.notFound().build());
	}

	@Override
	public ResponseEntity<InventarioEntity> actualizarArticulo(InventarioEntity articulo) {
		InventarioEntity articuloActualizado;
		Optional<InventarioEntity> articuloBD = articuloService.getArticuloById(articulo.getId());
		if (articuloBD.isPresent()) {
			articuloActualizado = articuloService.updateEmpleado(articulo);
			return new ResponseEntity<>(articuloActualizado, HttpStatus.OK);
		} else {
			return ResponseEntity.notFound().build();

		}
	}

	@Override
	public ResponseEntity<String> eliminarArticulo(Integer articuloId) {
		articuloService.deleteEmpleado(articuloId);
		return new ResponseEntity<String>("Articulo eliminado exitosamente", HttpStatus.OK);

	}

}
