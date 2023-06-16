package com.cibertec.edu.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cibertec.edu.entities.Socio;

public interface SocioRepository extends JpaRepository<Socio, Integer> {

	@Query("select s from Socio s where s.usuario.nombre = :usuario")
	public Socio findByUsuario(@Param("usuario") String usuario);
	
}
