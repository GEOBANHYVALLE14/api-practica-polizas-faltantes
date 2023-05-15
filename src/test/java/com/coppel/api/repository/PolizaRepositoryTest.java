package com.coppel.api.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.coppel.api.model.entity.PolizaEntity;
import com.coppel.api.utils.Utilidades;

@DataJpaTest
public class PolizaRepositoryTest {

	@Autowired
	private IPolizaRepository polizaRepository;

	private PolizaEntity poliza;

	@BeforeEach
	void setup() {
		poliza = PolizaEntity.builder().cantidad(100).empleadoGenero(1).fecha(Utilidades.fechaTimestamp()).sku(1)
				.build();
	}

	@DisplayName("Test para guardar una poliza")
	@Test
	void testGuardarPoliza() {
		// given - dado o condición previa o configuración
		PolizaEntity poliza1 = PolizaEntity.builder()
				.cantidad(100)
				.empleadoGenero(1)
				.fecha(Utilidades.fechaTimestamp())
				.sku(1)
				.build();

		// when - acción o el comportamiento que vamos a probar
		PolizaEntity polizaGuardada = polizaRepository.save(poliza1);

		// then - verificar la salida
		assertThat(polizaGuardada).isNotNull();
		assertThat(polizaGuardada.getId()).isGreaterThan(0);
	}

	@DisplayName("Test para listar las polizas")
	@Test
	void testListarPoliza() {
		// given
		PolizaEntity poliza1 = PolizaEntity.builder()
				.cantidad(100)
				.empleadoGenero(1)
				.fecha(Utilidades.fechaTimestamp())
				.sku(1)
				.build();
		
		polizaRepository.save(poliza1);
		polizaRepository.save(poliza);

		// when
		List<PolizaEntity> listaPolizas= polizaRepository.findAll();

		// then
		assertThat(listaPolizas).isNotNull();
		assertThat(listaPolizas.size()).isEqualTo(2);
	}

	@DisplayName("Test para obtener una poliza por ID")
	@Test
	void testObtenerPolizaPorId() {
		polizaRepository.save(poliza);

		// when - comportamiento o accion que vamos a probar
		PolizaEntity polizaBD = polizaRepository.findById(poliza.getId()).get();

		// then
		assertThat(polizaBD).isNotNull();
	}

	@DisplayName("Test para actualizar una poliza")
	@Test
	void testActualizarPoliza() {
		polizaRepository.save(poliza);

		// when
		PolizaEntity polizaGuardada = polizaRepository.findById(poliza.getId()).get();
		polizaGuardada.setCantidad(1999);
		polizaGuardada.setEmpleadoGenero(2);
		polizaGuardada.setSku(3);
		PolizaEntity polizaActualizada = polizaRepository.save(polizaGuardada);

		// then
		assertThat(polizaActualizada.getCantidad()).isEqualTo(1999);
		assertThat(polizaActualizada.getEmpleadoGenero()).isEqualTo(2);
	}

	@DisplayName("Test para eliminar una poliza")
	@Test
	void testEliminarPoliza() {
		polizaRepository.save(poliza);

		// when
		polizaRepository.deleteById(poliza.getId());
		Optional<PolizaEntity> polizaOptional = polizaRepository.findById(poliza.getId());

		// then
		assertThat(polizaOptional).isEmpty();
	}

}
