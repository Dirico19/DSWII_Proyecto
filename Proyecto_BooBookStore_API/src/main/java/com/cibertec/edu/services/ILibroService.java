package com.cibertec.edu.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.cibertec.edu.entities.Libro;

public interface ILibroService {

	public List<Libro> findAll();	
	
	public Page<Libro> findAll(Pageable pageable);
	
	public Libro save(Libro libro);
	
	public Libro findOne(Long id);
	
	public void delete(Long id);
	
	public void prestarLibro(Libro libro);
}
