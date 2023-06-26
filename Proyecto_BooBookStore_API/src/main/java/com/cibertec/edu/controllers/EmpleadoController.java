package com.cibertec.edu.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cibertec.edu.entities.Empleado;
import com.cibertec.edu.services.IEmpleadoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/empleados")
public class EmpleadoController {

	@Autowired
	private IEmpleadoService empleadoService;
	
	@GetMapping
	public ResponseEntity<Page<Empleado>> listado(
			@RequestParam(name = "page", defaultValue = "0", required = false) int page,
			@RequestParam(name = "size", defaultValue = "5", required = false) int size){
		Pageable pageRequest = PageRequest.of(page, size);
		Page<Empleado> empleados = empleadoService.findAll(pageRequest);
		return new ResponseEntity<>(empleados, HttpStatus.OK);
	}
	
}
