package com.cibertec.edu.services;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.cibertec.edu.entities.Pago;

public interface IPagoService {

	public List<Pago> findAll();
	
	public Page<Pago> findAll(Date fechaInicio, Date fechaFin, Pageable pageable);
	
	public Page<Pago> findByIdSocio(Date fechaInicio, Date fechaFin, int idSocio, Pageable pageable);
	
	public Pago save(int idPrestamo);
	
	public Pago findOne(int id);
	
}
