package com.cibertec.edu.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.cibertec.edu.entities.Libro;
import com.cibertec.edu.entities.Prestamo;
import com.cibertec.edu.entities.Socio;
import com.cibertec.edu.repositories.LibroRepository;
import com.cibertec.edu.repositories.PrestamoRepository;
import com.cibertec.edu.repositories.SocioRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class PrestamoService implements IPrestamoService {

	@Autowired
	private PrestamoRepository prestamoRepository;
	@Autowired
	private LibroRepository libroRepository;
	@Autowired
	private SocioRepository socioRepository;

	@Override
	public List<Prestamo> findAll() {
		return prestamoRepository.findAll();
	}

	@Override
	public Page<Prestamo> findAll(Date fechaInicio, Date fechaFin, Pageable pageable) {
		return prestamoRepository.findAll(fechaInicio, fechaFin, pageable);
	}

	@Override
	public Page<Prestamo> findByIdSocio(Date fechaInicio, Date fechaFin, int idSocio, Pageable pageable) {
		return prestamoRepository.findByIdSocio(fechaInicio, fechaFin, idSocio, pageable);
	}

	@Override
	public Prestamo save(long idLibro, int idSocio) {
		Libro libro = libroRepository.findById(idLibro).orElse(null);
		Socio socio = socioRepository.findById(idSocio).orElse(null);
		Prestamo prestamo = null;
		
		if (libro == null)
			throw new EntityNotFoundException("Libro no encontrado con id: " + idLibro);
		if (socio == null)
			throw new EntityNotFoundException("Socio no encontrado con id: " + idSocio);
		if (libro.getStock() <= 0)
			throw new IllegalArgumentException("El stock es insuficiente");
		if (libro != null && socio != null) {
			prestamo = new Prestamo();
			prestamo.setSocio(socio);
			prestamo.setLibro(libro);
			prestamo.setFecPrestamo(new Date());
			prestamo.setFecLimite(new Date(new Date().getTime() + 1728000000L));
			prestamo.setEstado("Pendiente");
			prestamo.setMora("No");
		}
		prestamo = prestamoRepository.save(prestamo);
		libro.setStock(libro.getStock() - 1);
		libroRepository.save(libro);
		
		return prestamo;
	}
	
	@Override
	public Prestamo update(int id) {
		Prestamo prestamo = prestamoRepository.findById(id).orElse(null);
		if (prestamo != null) {
			prestamo.setFecDevolucion(new Date());
			prestamo.setEstado("Devuelto");
			if (prestamo.getFecDevolucion().getTime() >= prestamo.getFecLimite().getTime())
				prestamo.setMora("Si");
		} else {
			throw new EntityNotFoundException("Prestamo no encontrado con id: " + id);
		}
		prestamo = prestamoRepository.save(prestamo);
		Libro libro = prestamo.getLibro();
		libro.setStock(libro.getStock() + 1);
		libroRepository.save(libro);
		return prestamo;
	}

	@Override
	public Prestamo findOne(int id) {
		Prestamo prestamo = prestamoRepository.findById(id).orElse(null);
		return prestamo;
	}

	@Override
	public int devolucionesPendientes(int id) {
		return prestamoRepository.devolucionesPendientes(id);
	}

	@Override
	public int morasPendientes(int id) {
		return prestamoRepository.morasPendientes(id);
	}

}
