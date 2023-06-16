package com.cibertec.edu.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cibertec.edu.entities.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {

}