package com.cibertec.edu.repositories;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cibertec.edu.entities.Prestamo;

public interface PrestamoRepository extends JpaRepository<Prestamo, Integer> {

	@Query("select count(1) from Prestamo p where p.socio.id=:idSocio and p.estado='Pendiente'")
	public int devolucionesPendientes(int idSocio);

	@Query("select count(1) from Prestamo p where p.socio.id=:idSocio and p.mora='Si'")
	public int morasPendientes(int idSocio);

	@Query("SELECT p FROM Prestamo p WHERE p.fecPrestamo >= ?1 AND p.fecPrestamo <= ?2")
	Page<Prestamo> findAll(Date fechaInicio, Date fechaFin, Pageable pageable);
	
	@Query("SELECT p FROM Prestamo p WHERE p.fecPrestamo >= ?1 AND p.fecPrestamo <= ?2 AND p.socio.id = ?3")
	Page<Prestamo> findByIdSocio(Date fechaInicio, Date fechaFin, int idSocio, Pageable pageable);

}
