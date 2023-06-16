package com.cibertec.edu.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cibertec.edu.entities.Libro;

public interface LibroRepository extends JpaRepository<Libro, Long> {

}