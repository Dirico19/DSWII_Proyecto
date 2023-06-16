package com.cibertec.edu.services;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.cibertec.edu.entities.Prestamo;

public interface IPrestamoService {

	public List<Prestamo> findAll();
	
	public Page<Prestamo> findAll(Date fechaInicio, Date fechaFin, Pageable pageable);
	
	public Page<Prestamo> findByIdSocio(Date fechaInicio, Date fechaFin, int idSocio, Pageable pageable);
	
	public Prestamo save(long idLibro, int idSocio);
	
	public Prestamo update(int id);
	
	public Prestamo findOne(int id);
	
	public int devolucionesPendientes(int id);
	
	public int morasPendientes(int id);
	
}
