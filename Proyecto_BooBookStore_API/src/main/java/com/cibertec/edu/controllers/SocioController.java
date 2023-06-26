package com.cibertec.edu.controllers;

import java.util.Date;

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

import com.cibertec.edu.entities.Socio;
import com.cibertec.edu.entities.Usuario;
import com.cibertec.edu.services.ISocioService;
import com.cibertec.edu.services.IUsuarioService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/socios")
public class SocioController {

	@Autowired
	private ISocioService socioService;

	@Autowired
	private IUsuarioService usuarioService;

	@GetMapping
	public ResponseEntity<Page<Socio>> listado(
			@RequestParam(name = "page", defaultValue = "0", required = false) int page,
			@RequestParam(name = "size", defaultValue = "5", required = false) int size) {
		Pageable pageRequest = PageRequest.of(page, size);
		Page<Socio> socios = socioService.findAll(pageRequest);
		return new ResponseEntity<>(socios, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Socio> consultar(@PathVariable(name = "id") int id) {
		Socio socio = socioService.findOne(id);
		if (socio == null)
			throw new EntityNotFoundException("Socio no encontrado con id: " + id);
		return new ResponseEntity<>(socio, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Socio> registrar(@Valid @RequestBody Socio socio) {
		Usuario usuario = usuarioService.findOne(socio.getUsuario().getId());
		socio.setUsuario(usuario);
		socio.setFecRegistro(new Date());
		Socio obj = socioService.save(socio);
		return new ResponseEntity<>(obj, HttpStatus.CREATED);
	}

	@PutMapping("/actualizar")
	public ResponseEntity<Socio> actualizar(@Valid @RequestBody Socio socio) {
		Socio obj = socioService.findOne(socio.getId());
		if (obj == null)
			throw new EntityNotFoundException("Socio no encontrado con el ID: " + socio.getId());
		Usuario usuario = usuarioService.findOne(socio.getUsuario().getId());
		socio.setUsuario(usuario);
		socio.setFecRegistro(obj.getFecRegistro());
		socio = socioService.save(socio);
		return new ResponseEntity<>(socio, HttpStatus.OK);
	}

	@DeleteMapping("/eliminar/{id}")
	public ResponseEntity<String> eliminar(@PathVariable(name = "id") int id) {
		Socio obj = socioService.findOne(id);
		if (obj == null)
			throw new EntityNotFoundException("Socio no encontrado con el ID: " + id);
		socioService.delete(id);
		return new ResponseEntity<>("Socio eliminado exitosamente", HttpStatus.OK);
	}
}