package com.cibertec.edu.services;

import java.util.List;

import com.cibertec.edu.entities.Rol;

public interface IRolService {

	public List<Rol> findAll();
	
	public Rol findOne(int id);
}
