package com.cibertec.edu.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cibertec.edu.entities.Empleado;

public interface EmpleadoRepository extends JpaRepository<Empleado, Integer> {

	@Query("select e from Empleado e where e.usuario.nombre = :usuario")
	public Empleado findByUsuario(@Param("usuario") String usuario);
	
}
