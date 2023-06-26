package com.cibertec.edu.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.cibertec.edu.entities.Socio;

public interface ISocioService {

	public List<Socio> findAll();
	
	public Page<Socio> findAll(Pageable pageable);
	
	public Socio save(Socio socio);
	
	public Socio findOne(int id);
	
	public void delete(int id);
	
	public Socio findbyUsuario(String usuario);
	
}
