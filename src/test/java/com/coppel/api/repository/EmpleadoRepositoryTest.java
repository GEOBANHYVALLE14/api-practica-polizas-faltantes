package com.coppel.api.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.coppel.api.model.entity.EmpleadoEntity;

@DataJpaTest
public class EmpleadoRepositoryTest {
	
	@Autowired
	private EmpleadoRepository empleadoRepository;
	
	private EmpleadoEntity empleado;
   
	@BeforeEach
    void setup(){
		empleado = EmpleadoEntity.builder()
                .nombre("Christian")
                .apellido("Ramirez")
                .puesto("GERENTE")
                .build();
    }
	
	@DisplayName("Test para guardar un empleado")
    @Test
	void testGuardarEmpleado() {
		// given - dado o condición previa o configuración
		EmpleadoEntity empleado1 =  EmpleadoEntity.builder()
                .nombre("GREY")
                .apellido("VALLE")
                .puesto("CAJERO")
                .build();

		// when - acción o el comportamiento que vamos a probar
		EmpleadoEntity empleadoGuardado = empleadoRepository.save(empleado1);

		// then - verificar la salida
		assertThat(empleadoGuardado).isNotNull();
		assertThat(empleadoGuardado.getIdEmpleado()).isGreaterThan(0);
	}

	
	 @DisplayName("Test para listar a los empleados")
	    @Test
	    void testListarEmpleados(){
	        //given
		 EmpleadoEntity empleado1 = EmpleadoEntity.builder()
	                .nombre("Julen")
	                .apellido("Oliva")
	                .puesto("ARQUITECTO")
	                .build();
	        empleadoRepository.save(empleado1);
	        empleadoRepository.save(empleado);

	        //when
	        List<EmpleadoEntity> listaEmpleados = empleadoRepository.findAll();

	        //then
	        assertThat(listaEmpleados).isNotNull();
	        assertThat(listaEmpleados.size()).isEqualTo(2);
	    }
	
		@DisplayName("Test para obtener un empleado por ID")
		@Test
		void testObtenerEmpleadoPorId() {
			empleadoRepository.save(empleado);

			// when - comportamiento o accion que vamos a probar
			EmpleadoEntity empleadoBD = empleadoRepository.findById(empleado.getIdEmpleado()).get();

			// then
			assertThat(empleadoBD).isNotNull();
		}
		
		@DisplayName("Test para actualizar un empleado")
	    @Test
	    void testActualizarEmpleado(){
	        empleadoRepository.save(empleado);

	        //when
	        EmpleadoEntity empleadoGuardado = empleadoRepository.findById(empleado.getIdEmpleado()).get();
	        empleadoGuardado.setPuesto("SEGURIDAD");
	        empleadoGuardado.setNombre("Christian Raul");
	        empleadoGuardado.setApellido("Ramirez Cucitini");
	        EmpleadoEntity empleadoActualizado = empleadoRepository.save(empleadoGuardado);

	        //then
	        assertThat(empleadoActualizado.getPuesto()).isEqualTo("SEGURIDAD");
	        assertThat(empleadoActualizado.getNombre()).isEqualTo("Christian Raul");
	    }
		
		@DisplayName("Test para eliminar un empleado")
	    @Test
	    void testEliminarEmpleado(){
	        empleadoRepository.save(empleado);

	        //when
	        empleadoRepository.deleteById(empleado.getIdEmpleado());
	        Optional<EmpleadoEntity> empleadoOptional = empleadoRepository.findById(empleado.getIdEmpleado());

	        //then
	        assertThat(empleadoOptional).isEmpty();
	    }
	 
	
}
