package com.cibertec.edu.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cibertec.edu.entities.Empleado;
import com.cibertec.edu.entities.Usuario;
import com.cibertec.edu.services.IEmpleadoService;
import com.cibertec.edu.services.IUsuarioService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/empleados")
public class EmpleadoController {

	@Autowired
	private IEmpleadoService empleadoService;
	@Autowired
	private IUsuarioService usuarioService;
	
	@PostMapping
	public ResponseEntity<Empleado> registrar(@Valid @RequestBody Empleado empleado) {
		Usuario usuario = usuarioService.findOne(empleado.getUsuario().getId());
		empleado.setUsuario(usuario);
		Empleado obj = empleadoService.save(empleado);
		return new ResponseEntity<>(obj, HttpStatus.CREATED);
	}
	
}
