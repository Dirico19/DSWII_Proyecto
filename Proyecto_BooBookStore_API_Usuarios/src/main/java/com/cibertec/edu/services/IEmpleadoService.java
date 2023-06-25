package com.cibertec.edu.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.cibertec.edu.entities.Empleado;

public interface IEmpleadoService {

	public List<Empleado> findAll();
	
	public Page<Empleado> findAll(Pageable pageable);
	
	public void save(Empleado empleado);
	
	public Empleado findOne(int id);
	
	public void delete(int id);
	
	public Empleado findByUsuario(String usuario);
	
}
