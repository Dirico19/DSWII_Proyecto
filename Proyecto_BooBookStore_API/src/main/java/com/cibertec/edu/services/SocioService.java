package com.cibertec.edu.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.cibertec.edu.entities.Socio;
import com.cibertec.edu.repositories.SocioRepository;

@Service
public class SocioService implements ISocioService {

	@Autowired
	private SocioRepository socioRepository;
	
	@Override
	public List<Socio> findAll() {
		return socioRepository.findAll();
	}

	@Override
	public Page<Socio> findAll(Pageable pageable) {
		return socioRepository.findAll(pageable);
	}

	@Override
	public Socio save(Socio socio) {
		return socioRepository.save(socio);
	}

	@Override
	public Socio findOne(int id) {
		return socioRepository.findById(id).orElse(null);
	}

	@Override
	public void delete(int id) {
		socioRepository.deleteById(id);
	}

	@Override
	public Socio findbyUsuario(String usuario) {
		return socioRepository.findByUsuario(usuario);
	}

}
