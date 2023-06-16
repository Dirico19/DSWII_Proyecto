package com.cibertec.edu.services;

import java.util.Date;
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
	public Page<Pago> findAll(Date fechaInicio, Date fechaFin, Pageable pageable) {
		return pagoRepository.findAll(fechaInicio, fechaFin, pageable);
	}

	@Override
	public Page<Pago> findByIdSocio(Date fechaInicio, Date fechaFin, int idSocio, Pageable pageable) {
		return pagoRepository.findByIdSocio(fechaInicio, fechaFin, idSocio, pageable);
	}

	@Override
	public Pago save(int idPrestamo) {
		Prestamo prestamo = prestamoRepository.findById(idPrestamo).orElse(null);
		Pago pago = null;
		if (prestamo != null && prestamo.getMora().equals("Si")) {
			pago = new Pago();
			pago.setPrestamo(prestamo);
			pago.setFecPago(new Date());
			pago.setMonto(5);
		}
		pago = pagoRepository.save(pago);
		prestamo.setMora("No");
		prestamoRepository.save(prestamo);
		
		return pago;
	}

	@Override
	public Pago findOne(int id) {
		return pagoRepository.findById(id).orElse(null);
	}

}
