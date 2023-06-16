package com.cibertec.edu.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.cibertec.edu.entities.Pago;

public interface IPagoService {

	public List<Pago> findAll();
	
	public Page<Pago> findAll(Pageable pageable);
	
	public void save(Pago pago);
	
	public Pago findOne(int id);
	
	public List<Pago> findByIdSocio(int idSocio);
	
	public List<Pago> findByDate(String fecha);
	
	public List<Pago> findByDateAndSocio(String fecha, int idSocio);
	
}
