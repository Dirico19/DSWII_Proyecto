package com.cibertec.edu.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cibertec.edu.entities.Rol;
import com.cibertec.edu.repositories.RolRepository;

@Service
public class RolService implements IRolService {

	@Autowired
	private RolRepository rolRepository;
	
	@Override
	public List<Rol> findAll() {
		return rolRepository.findAll();
	}

	@Override
	public Rol findOne(int id) {
		return rolRepository.findById(id).orElse(null);
	}

}
