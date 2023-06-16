package com.cibertec.edu.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cibertec.edu.entities.Categoria;
import com.cibertec.edu.repositories.CategoriaRepository;

@Service
public class CategoriaService implements ICategoriaService {

	@Autowired
	private CategoriaRepository categoriaRepo;
	
	@Override
	@Transactional(readOnly = true)
	public List<Categoria> findAll() {
		return categoriaRepo.findAll();
	}

}
