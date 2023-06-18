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

import com.cibertec.edu.entities.Libro;
import com.cibertec.edu.services.ILibroService;

@RestController
@RequestMapping("/api/libros")
public class LibroController {

	@Autowired
	private ILibroService libroService;
	
	@GetMapping
	public ResponseEntity<Page<Libro>> listado(
			@RequestParam(name = "page", defaultValue = "0", required = false) int page,
			@RequestParam(name = "size", defaultValue = "5", required = false) int size) {
		Pageable pageRequest = PageRequest.of(page, size);
		Page<Libro> libros = libroService.findAll(pageRequest);
		return new ResponseEntity<>(libros, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Libro> consultar(@PathVariable(name = "id") long id) {
		Libro libro = libroService.findOne(id);
		if (libro != null)
			return new ResponseEntity<>(libro, HttpStatus.OK);
		else
			return new ResponseEntity<>(libro, HttpStatus.BAD_REQUEST);
	}
	
	@PostMapping("/registrar")
	public ResponseEntity<Libro> registrar(@RequestBody Libro libro) {
		libro = libroService.save(libro);
		return new ResponseEntity<>(libro, HttpStatus.CREATED);
	}
	
	@PutMapping("/actualizar")
	public ResponseEntity<Libro> actualizar(@RequestBody Libro libro) {
		if (libro.getId() == 0)
			libro = null;
		libro = libroService.save(libro);
		return new ResponseEntity<>(libro, HttpStatus.OK);
	}
	
	@DeleteMapping("/eliminar/{id}")
	public ResponseEntity<String> eliminar(@PathVariable(name = "id") long id) {
		libroService.delete(id);
		return new ResponseEntity<>("Libro eliminado exitosamente", HttpStatus.OK);
	}
	
}
