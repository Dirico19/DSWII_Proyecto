package com.cibertec.edu.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.cibertec.edu.entities.Pago;
import com.cibertec.edu.entities.Prestamo;
import com.cibertec.edu.repositories.PagoRepository;
import com.cibertec.edu.repositories.PrestamoRepository;

@Service
public class PagoService implements IPagoService {

	@Autowired
	private PagoRepository pagoRepository;
	@Autowired
	private PrestamoRepository prestamoRepository;
	
	@Override
	public List<Pago> findAll() {
		return pagoRepository.findAll();
	}

	@Override
	public Page<Pago> findAll(Pageable pageable) {
		return pagoRepository.findAll(pageable);
	}

	@Override
	public void save(Pago pago) {
		pagoRepository.save(pago);
		Prestamo prestamo = pago.getPrestamo();
		prestamo.setMora("No");
		prestamoRepository.save(prestamo);
	}

	@Override
	public Pago findOne(int id) {
		return pagoRepository.findById(id).orElse(null);
	}
	
	@Override
	public List<Pago> findByIdSocio(int idSocio) {
		return pagoRepository.findByIdSocio(idSocio);
	}

	@Override
	public List<Pago> findByDate(String fecha) {
		return pagoRepository.findByDate(fecha);
	}

	@Override
	public List<Pago> findByDateAndSocio(String fecha, int idSocio) {
		return pagoRepository.findByDateAndSocio(fecha, idSocio);
	}

}
