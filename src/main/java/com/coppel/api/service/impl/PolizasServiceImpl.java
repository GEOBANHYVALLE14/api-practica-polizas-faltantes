package com.coppel.api.service.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coppel.api.controller.impl.PolizasControllerImpl;
import com.coppel.api.model.Articulo;
import com.coppel.api.model.Data;
import com.coppel.api.model.Empleado;
import com.coppel.api.model.Meta;
import com.coppel.api.model.Poliza;
import com.coppel.api.model.RequestGrabarPoliza;
import com.coppel.api.model.ResponseCoppel;
import com.coppel.api.model.entity.EmpleadoEntity;
import com.coppel.api.model.entity.InventarioEntity;
import com.coppel.api.model.entity.PolizaEntity;
import com.coppel.api.repository.EmpleadoRepository;
import com.coppel.api.repository.IPolizaRepository;
import com.coppel.api.repository.InventarioRepository;
import com.coppel.api.repository.PolizaRepository;
import com.coppel.api.service.PolizasService;
import com.coppel.api.utils.Utilidades;

@Service
public class PolizasServiceImpl implements PolizasService {
	private static final Logger logger = LoggerFactory.getLogger(PolizasControllerImpl.class);

	private IPolizaRepository iPolizaRepository;
	private PolizaRepository polizaRepository;
	private EmpleadoRepository empleadoRepository;
	private InventarioRepository inventarioRepository;

	@Autowired
	public PolizasServiceImpl(IPolizaRepository iPolizaRepository, PolizaRepository polizaRepository,
			EmpleadoRepository empleadoRepository, InventarioRepository inventarioRepository) {
		this.iPolizaRepository = iPolizaRepository;
		this.polizaRepository = polizaRepository;
		this.empleadoRepository = empleadoRepository;
		this.inventarioRepository = inventarioRepository;
	}

	@Override
	@Transactional
	public ResponseCoppel grabarPoliza(RequestGrabarPoliza request) {

		ResponseCoppel response = null;
		Optional<EmpleadoEntity> empleadoEntity = null;
		Optional<InventarioEntity> inventarioEntity = null;
		// PolizaEntity polizaEntity = new PolizaEntity();
		Data data = new Data();
		Poliza poliza = new Poliza();
		Empleado empleado = new Empleado();
		Articulo articulo = new Articulo();

		try {

			empleadoEntity = empleadoRepository.findById(request.getEmpleadoGenero());
			// logger.info("empleado: {}", empleadoEntity);

			if (empleadoEntity.isPresent()) {
				logger.info("EMPLEADO OK");
				inventarioEntity = inventarioRepository.findById(request.getSku());

				// logger.info("inventario: {}", inventarioEntity);

				if (inventarioEntity.isPresent()) {
					logger.info("ARTICULO OK");

					// VALIDAR SI HAY STOCK SUFICIENTE
					if ((inventarioEntity.get().getCantidad() - request.getCantidad()) >= 0) {
						logger.info("STOCK SUFICIENTE");

						// SE VALIDA DE NUEVO EL STOCK, GUARDA LA POLIZA Y SE RETORNA IDPOLIZA
						int resp = polizaRepository.grabarPoliza(request.getEmpleadoGenero(), request.getSku(),
								request.getCantidad(), Utilidades.fechaTimestamp());

						logger.info("POLIZA OK? " + resp);
						if (resp > 0) {
							/*
							 * polizaEntity = iPolizaRepository.save(new
							 * PolizaEntity(request.getEmpleadoGenero(), request.getSku(),
							 * request.getCantidad(), Utilidades.fechaTimestamp()));
							 * logger.info("POLIZA GUARDADA: {}", polizaEntity.getId());
							 * poliza.setCantidad(polizaEntity.getCantidad());
							 * poliza.setIdPoliza(polizaEntity.getId());
							 */
							poliza.setCantidad(request.getCantidad());
							poliza.setIdPoliza(resp);

							empleado.setNombre(empleadoEntity.get().getNombre());
							empleado.setApellido(empleadoEntity.get().getApellido());

							articulo.setNombre(inventarioEntity.get().getNombre());
							articulo.setSku(inventarioEntity.get().getId());

							data.setPoliza(poliza);
							data.setEmpleado(empleado);
							data.setDetalleArticulo(articulo);

							response = new ResponseCoppel(new Meta("OK"), data);
						} else {
							logger.error("NO SE GUARDO LA POLIZA");
							response = new ResponseCoppel(new Meta("FAILURE"),
									new Data("Ha ocurrido un error en los grabados de póliza.", ""));
						}

					} else {
						logger.error("NO HAY STOCK SUFICIENTE");
						response = new ResponseCoppel(new Meta("FAILURE"),
								new Data("Ha ocurrido un error en los grabados de póliza.", ""));
					}

				} else {
					logger.error("FALLA: EL ARTICULO NO EXISTE");

					response = new ResponseCoppel(new Meta("FAILURE"),
							new Data("Ha ocurrido un error en los grabados de póliza.", ""));
				}

			} else {
				logger.error("FALLA: EL EMPLEADO NO EXISTE");

				response = new ResponseCoppel(new Meta("FAILURE"),
						new Data("Ha ocurrido un error en los grabados de póliza.", ""));
			}

		} catch (IllegalArgumentException | OptimisticLockingFailureException e) {
			logger.error("Exception: {}", e.getMessage());

			response = new ResponseCoppel(new Meta("FAILURE"),
					new Data("Ha ocurrido un error en los grabados de póliza.", ""));

		} finally {
			empleadoEntity = null;
			inventarioEntity = null;
			// polizaEntity = null;
			data = null;
			poliza = null;
			empleado = null;
			articulo = null;
		}

		return response;
	}

	@Transactional(readOnly = true)
	@Override
	public ResponseCoppel mostrarPoliza(Integer id) {
		Poliza poliza = new Poliza();
		Empleado empleado = new Empleado();
		EmpleadoEntity empleadoBD = new EmpleadoEntity();
		InventarioEntity articuloBD = new InventarioEntity();
		Articulo articulo = new Articulo();
		Data data = new Data();
		ResponseCoppel response = new ResponseCoppel();
		Optional<PolizaEntity> polizaBD;
		try {
			logger.error("idPoliza: {}", id);

			polizaBD = iPolizaRepository.findByIdPoliza(id);

			if (polizaBD.isPresent()) {

				poliza.setCantidad(polizaBD.get().getCantidad());
				poliza.setIdPoliza(polizaBD.get().getId());

				empleadoBD = empleadoRepository.findById(polizaBD.get().getEmpleadoGenero()).get();

				empleado.setId(empleadoBD.getIdEmpleado());
				empleado.setNombre(empleadoBD.getNombre());
				empleado.setApellido(empleadoBD.getNombre());

				articuloBD = inventarioRepository.findBySku(polizaBD.get().getSku()).get();

				articulo.setSku(articuloBD.getId());
				articulo.setNombre(articuloBD.getNombre());

				data.setPoliza(poliza);
				data.setEmpleado(empleado);
				data.setDetalleArticulo(articulo);

				response = new ResponseCoppel(new Meta("OK"), data);
			} else {
				logger.error("FALLA EN POLIZA: NO EXISTE");

				response = new ResponseCoppel(new Meta("FAILURE"),
						new Data("Ha ocurrido un error al consultar la póliza.", ""));
			}
		} catch (IllegalArgumentException | OptimisticLockingFailureException e) {
			logger.error("Exception: {}", e.getMessage());

			response = new ResponseCoppel(new Meta("FAILURE"),
					new Data("Ha ocurrido un error al consultar la póliza.", ""));

		} finally {
			poliza = null;
			empleado = null;
			empleadoBD = null;
			articuloBD = null;
			articulo = null;
			data = null;
			polizaBD = null;

		}
		return response;
	}

	@Transactional
	@Override
	public ResponseCoppel actualizarPoliza(RequestGrabarPoliza request) {

		ResponseCoppel response = null;
		Optional<EmpleadoEntity> empleadoEntity = null;
		Optional<InventarioEntity> inventarioEntity = null;
		Optional<PolizaEntity> polizaEntity = null;
		Data data = new Data();
		Poliza poliza = new Poliza();
		Empleado empleado = new Empleado();
		Articulo articulo = new Articulo();

		try {

			empleadoEntity = empleadoRepository.findById(request.getEmpleadoGenero());
			// logger.info("empleado: {}", empleadoEntity);

			if (empleadoEntity.isPresent()) {
				logger.info("EMPLEADO OK");
				inventarioEntity = inventarioRepository.findById(request.getSku());

				// logger.info("inventario: {}", inventarioEntity);

				if (inventarioEntity.isPresent()) {
					logger.info("ARTICULO OK");

					polizaEntity = iPolizaRepository.findById(request.getIdPoliza());

					if (polizaEntity.isPresent()) {

						// EL CAMPO CANTIDAD NO SE ACTUALIZA
						PolizaEntity polizaBD = iPolizaRepository.save(
								new PolizaEntity(request.getIdPoliza(), request.getEmpleadoGenero(), request.getSku(),
										polizaEntity.get().getCantidad(), Utilidades.fechaTimestamp()));

						logger.info("POLIZA ACTUALIZADA: {}", polizaBD.getId());

						poliza.setCantidad(polizaBD.getCantidad());
						poliza.setIdPoliza(polizaBD.getId());

						empleado.setNombre(empleadoEntity.get().getNombre());
						empleado.setApellido(empleadoEntity.get().getApellido());

						articulo.setNombre(inventarioEntity.get().getNombre());
						articulo.setSku(inventarioEntity.get().getId());

						data.setPoliza(poliza);
						data.setEmpleado(empleado);
						data.setDetalleArticulo(articulo);

						response = new ResponseCoppel(new Meta("OK"), data);
					} else {
						logger.error("FALLA: POLIZA NO EXISTE");

						response = new ResponseCoppel(new Meta("FAILURE"),
								new Data("Ha ocurrido un error al intentar actualizar la póliza.", ""));
					}

				} else {
					logger.error("FALLA: ARTICULO NO EXISTE");

					response = new ResponseCoppel(new Meta("FAILURE"),
							new Data("Ha ocurrido un error al intentar actualizar la póliza.", ""));
				}

			} else {
				logger.error("FALLA: EMPLEADO NO EXISTE");

				response = new ResponseCoppel(new Meta("FAILURE"),
						new Data("Ha ocurrido un error al intentar actualizar la póliza.", ""));
			}

		} catch (IllegalArgumentException | OptimisticLockingFailureException e) {
			logger.error("Exception: {}", e.getMessage());

			response = new ResponseCoppel(new Meta("FAILURE"),
					new Data("Ha ocurrido un error al intentar actualizar la póliza.", ""));

		} finally {
			empleadoEntity = null;
			inventarioEntity = null;
			polizaEntity = null;
			data = null;
			poliza = null;
			empleado = null;
			articulo = null;
		}

		return response;

	}

	@Transactional
	@Override
	public ResponseCoppel eliminarPoliza(Integer id) {
		ResponseCoppel response = null;

		try {
			Optional<PolizaEntity> polizaEntity = iPolizaRepository.findByIdPoliza(id);

			if (polizaEntity.isPresent()) {
				logger.info("POLIZA OK");
				logger.info("OBJ: {}", polizaEntity.get().toString());

				// iPolizaRepository.deleteByIdPoliza(id);
				// iPolizaRepository.deleteById(id);
				int resp = polizaRepository.eliminarPoliza(id);
				if (resp > 0) {
					logger.info("ELIMINO POLIZA: OK");

					response = new ResponseCoppel(new Meta("OK"),
							new Data(String.format("Se eliminó correctamente la poliza %s", id), ""));
				} else {
					logger.error("FALLA AL INTENTAR ELIMINAR POLIZA: {}", id);

					response = new ResponseCoppel(new Meta("FAILURE"),
							new Data("Ha ocurrido un error al intentar eliminar la póliza.", ""));
				}

			} else {
				logger.error("FALLA DE POLIZA: NO EXISTE");

				response = new ResponseCoppel(new Meta("FAILURE"),
						new Data("Ha ocurrido un error al intentar eliminar la póliza.", ""));
			}
		} catch (IllegalArgumentException e) {
			logger.error("Exception: {}", e.getMessage());
			response = new ResponseCoppel(new Meta("FAILURE"),
					new Data("Ha ocurrido un error al intentar eliminar la póliza.", ""));
		}

		return response;
	}

	@Transactional(readOnly = true)
	@Override
	public List<PolizaEntity> getAllPolizas() {
		return iPolizaRepository.findAll();
	}

}
