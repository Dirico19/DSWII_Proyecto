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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cibertec.edu.entities.Prestamo;
import com.cibertec.edu.services.IPrestamoService;

@RestController
@RequestMapping("/api/prestamos")
public class PrestamoController {

	@Autowired
	private IPrestamoService prestamoService;
	
	@GetMapping
	public Page<Prestamo> listado(
			@RequestParam(name = "page", defaultValue = "0", required = false) int page,
			@RequestParam(name = "size", defaultValue = "5", required = false) int size,
			@RequestParam(name="fechaInicio", defaultValue = "1900-01-01", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaInicio,
			@RequestParam(name="fechaFin", defaultValue = "3000-01-01", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaFin) {
		Pageable pageRequest = PageRequest.of(page, size);
		Page<Prestamo> prestamos = prestamoService.findAll(fechaInicio, fechaFin, pageRequest);
		return prestamos;
	}
	
	@GetMapping("/socio/{id}")
	public Page<Prestamo> listadoPorSocio(
			@RequestParam(name = "page", defaultValue = "0", required = false) int page,
			@RequestParam(name = "size", defaultValue = "5", required = false) int size,
			@RequestParam(name="fechaInicio", defaultValue = "1900-01-01", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaInicio,
			@RequestParam(name="fechaFin", defaultValue = "3000-01-01", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaFin,
			@PathVariable(name = "id") int idSocio) {
		Pageable pageRequest = PageRequest.of(page, size);
		Page<Prestamo> prestamos = prestamoService.findByIdSocio(fechaInicio, fechaFin, idSocio, pageRequest);
		return prestamos;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Prestamo> consultar(@PathVariable(name = "id") int id) {
		Prestamo prestamo = prestamoService.findOne(id);
		return new ResponseEntity<>(prestamo, HttpStatus.OK);
	}
	
	@PostMapping("/confirmar/{idLibro}")
	public ResponseEntity<Prestamo> confirmar(@PathVariable(name = "idLibro") long idLibro, @RequestBody int idSocio) {
		Prestamo prestamo = prestamoService.save(idLibro, idSocio);
		return new ResponseEntity<>(prestamo, HttpStatus.CREATED);
	}
	
	@PutMapping("/devolver/{id}")
	public ResponseEntity<Prestamo> devolver(@PathVariable(name = "id") int id) {
		Prestamo prestamo = prestamoService.update(id);
		return new ResponseEntity<>(prestamo, HttpStatus.OK);
	}
	
}
