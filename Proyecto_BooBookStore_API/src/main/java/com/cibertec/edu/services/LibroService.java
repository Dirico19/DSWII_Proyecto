package com.cibertec.edu.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cibertec.edu.entities.Libro;
import com.cibertec.edu.repositories.LibroRepository;


@Service
public class LibroService implements ILibroService {

	@Autowired
	private LibroRepository libroRepository;	
	
	@Override
	@Transactional(readOnly = true)
	public List<Libro> findAll() {
		return libroRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Libro> findAll(Pageable pageable) {
		return libroRepository.findAll(pageable);
	}

	@Override
	@Transactional
	public Libro save(Libro libro) {
		return libroRepository.save(libro);
	}

	@Override
	@Transactional(readOnly = true)
	public Libro findOne(Long id) {
		return libroRepository.findById(id).orElse(null);		
	}

	@Override
	public void delete(Long id) {
		Libro libro = libroRepository.findById(id).orElse(null);
		libroRepository.delete(libro);
	}
	
	@Override
	@Transactional
	public void prestarLibro(Libro libro) {
		libro.setStock(libro.getStock()-1);
		libroRepository.save(libro);
	}


	
}
