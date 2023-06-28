package com.cibertec.edu.controllers;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cibertec.edu.entities.Pago;
import com.cibertec.edu.services.IPagoService;

import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/api/pagos")
public class PagoController {

	@Autowired
	private IPagoService pagoService;
	
	@GetMapping
	public Page<Pago> listado(
			@RequestParam(name = "page", defaultValue = "0", required = false) int page,
			@RequestParam(name = "size", defaultValue = "5", required = false) int size,
			@RequestParam(name="fechaInicio", defaultValue = "1900-01-01", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaInicio,
			@RequestParam(name="fechaFin", defaultValue = "3000-01-01", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaFin) {
		Pageable pageRequest = PageRequest.of(page, size);
		Page<Pago> pagos = pagoService.findAll(fechaInicio, fechaFin, pageRequest);
		return pagos;
	}
	
	@GetMapping("/socio/{id}")
	public Page<Pago> listadoPorSocio(
			@RequestParam(name = "page", defaultValue = "0", required = false) int page,
			@RequestParam(name = "size", defaultValue = "5", required = false) int size,
			@RequestParam(name="fechaInicio", defaultValue = "1900-01-01", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaInicio,
			@RequestParam(name="fechaFin", defaultValue = "3000-01-01", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaFin,
			@PathVariable(name = "id") int idSocio) {
		Pageable pageRequest = PageRequest.of(page, size);
		Page<Pago> pagos = pagoService.findByIdSocio(fechaInicio, fechaFin, idSocio, pageRequest);
		return pagos;
	}
	
	@PostMapping("/confirmar/{idPrestamo}")
	public ResponseEntity<Pago> confirmar(@PathVariable(name = "idPrestamo") int idPrestamo) {
		Pago pago = pagoService.save(idPrestamo);
		return new ResponseEntity<>(pago, HttpStatus.CREATED);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Pago> consultar(@PathVariable(name = "id") int id) {
		Pago pago = pagoService.findOne(id);
		if (pago == null)
			throw new EntityNotFoundException("Pago no encontrado con id: " + id);
		return new ResponseEntity<>(pago, HttpStatus.OK);
	}
	
}
