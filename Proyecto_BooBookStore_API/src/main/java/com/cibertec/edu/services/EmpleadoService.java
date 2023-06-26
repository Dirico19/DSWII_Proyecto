package com.cibertec.edu.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.cibertec.edu.entities.Empleado;
import com.cibertec.edu.repositories.EmpleadoRepository;

@Service
public class EmpleadoService implements IEmpleadoService {

	@Autowired
	private EmpleadoRepository empleadoRepository;
	
	@Override
	public List<Empleado> findAll() {
		return empleadoRepository.findAll();
	}

	@Override
	public Page<Empleado> findAll(Pageable pageable) {
		return empleadoRepository.findAll(pageable);
	}

	@Override
	public Empleado save(Empleado empleado) {
		return empleadoRepository.save(empleado);
	}

	@Override
	public Empleado findOne(int id) {
		return empleadoRepository.findById(id).orElse(null);
	}

	@Override
	public void delete(int id) {
		empleadoRepository.deleteById(id);
	}

	@Override
	public Empleado findByUsuario(String usuario) {
		return empleadoRepository.findByUsuario(usuario);
	}

}
