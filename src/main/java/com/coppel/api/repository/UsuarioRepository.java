package com.coppel.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.coppel.api.model.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
	static final String CONSULTA_1 = "SELECT id, username, nombre, password, idEmpleado, enabled FROM users WHERE username = :username";

	@Query(value = CONSULTA_1, nativeQuery = true)
	Optional<Usuario> findOneByUsername(String username);
}
