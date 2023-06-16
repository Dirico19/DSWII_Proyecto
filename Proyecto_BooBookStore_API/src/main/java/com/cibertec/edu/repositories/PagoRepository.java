package com.cibertec.edu.repositories;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cibertec.edu.entities.Pago;

public interface PagoRepository extends JpaRepository<Pago, Integer> {

	@Query("SELECT p FROM Pago p INNER JOIN p.prestamo pr INNER JOIN pr.socio s WHERE p.fecPago >= ?1 AND p.fecPago <= ?2")
	Page<Pago> findAll(Date fechaInicio, Date fechaFin, Pageable pageable);

	@Query("SELECT p FROM Pago p INNER JOIN p.prestamo pr INNER JOIN pr.socio s WHERE p.fecPago >= ?1 AND p.fecPago <= ?2 AND s.id = ?3")
	Page<Pago> findByIdSocio(Date fechaInicio, Date fechaFin, int idSocio, Pageable pageable);
	
}
