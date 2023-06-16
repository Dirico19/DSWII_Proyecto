package com.cibertec.edu.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cibertec.edu.entities.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
	
	Usuario findByNombre(String nombre);
}