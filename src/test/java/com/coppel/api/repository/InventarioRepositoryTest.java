package com.coppel.api.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.coppel.api.model.entity.InventarioEntity;

@DataJpaTest
public class InventarioRepositoryTest {

	@Autowired
	private InventarioRepository inventarioRepository;

	private InventarioEntity articulo;
	
	@BeforeEach
    void setup(){
		articulo = InventarioEntity.builder()
                //.sku(1)
                .nombre("TELEFONO")
                .cantidad(1000)
                .build();
    }
	
	@DisplayName("Test para guardar un articulo")
    @Test
	void testGuardarArticulo() {
		// given - dado o condición previa o configuración
		InventarioEntity empleado1 =  InventarioEntity.builder()
				//.sku(10001)
                .nombre("TELEFONO")
                .cantidad(1000)
                .build();

		// when - acción o el comportamiento que vamos a probar
		InventarioEntity articuloGuardado = inventarioRepository.save(empleado1);

		// then - verificar la salida
		assertThat(articuloGuardado).isNotNull();
		assertThat(articuloGuardado.getId()).isGreaterThan(0);
	}

	
	 @DisplayName("Test para listar a los Articulos")
	    @Test
	    void testListarArticulos(){
	        //given
		 InventarioEntity articulo1 = InventarioEntity.builder()
				 //.sku(10001)
	                .nombre("TELEFONO")
	                .cantidad(1000)
	                .build();
	        inventarioRepository.save(articulo1);
	        inventarioRepository.save(articulo);

	        //when
	        List<InventarioEntity> listaArticulos = inventarioRepository.findAll();

	        //then
	        assertThat(listaArticulos).isNotNull();
	        assertThat(listaArticulos.size()).isEqualTo(2);
	    }
	
		@DisplayName("Test para obtener un Articulo por ID")
		@Test
		void testObtenerArticuloPorSku() {
			inventarioRepository.save(articulo);

			// when - comportamiento o accion que vamos a probar
			InventarioEntity articuloBD = inventarioRepository.findById(articulo.getId()).get();

			// then
			assertThat(articuloBD).isNotNull();
		}
		
		@DisplayName("Test para actualizar un Articulo")
	    @Test
	    void testActualizarArticulo(){
			inventarioRepository.save(articulo);

	        //when
	        InventarioEntity articuloGuardado = inventarioRepository.findById(articulo.getId()).get();
	        articuloGuardado.setCantidad(1);
	        articuloGuardado.setNombre("ESCOBA");
	        InventarioEntity articuloActualizado = inventarioRepository.save(articuloGuardado);

	        //then
	        assertThat(articuloActualizado.getCantidad()).isEqualTo(1);
	        assertThat(articuloActualizado.getNombre()).isEqualTo("ESCOBA");
	    }
		
		@DisplayName("Test para eliminar un Articulo")
		@Test
		void testEliminarArticulo() {
			inventarioRepository.save(articulo);

			// when
			inventarioRepository.deleteById(articulo.getId());
			Optional<InventarioEntity> articuloOptional = inventarioRepository.findById(articulo.getId());

			// then
			assertThat(articuloOptional).isEmpty();
		}
	}
