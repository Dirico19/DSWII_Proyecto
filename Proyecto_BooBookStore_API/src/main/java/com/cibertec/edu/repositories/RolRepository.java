package com.cibertec.edu.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cibertec.edu.entities.Rol;

public interface RolRepository extends JpaRepository<Rol, Integer> {
	
}
