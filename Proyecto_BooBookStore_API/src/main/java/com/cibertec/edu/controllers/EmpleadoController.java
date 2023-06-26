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
import com.cibertec.edu.entities.Usuario;
import com.cibertec.edu.services.IEmpleadoService;
import com.cibertec.edu.services.IUsuarioService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/empleados")
public class EmpleadoController {

	@Autowired
	private IEmpleadoService empleadoService;
	@Autowired
	private IUsuarioService usuarioService;
	
	@GetMapping
	public ResponseEntity<Page<Empleado>> listado(
			@RequestParam(name = "page", defaultValue = "0", required = false) int page,
			@RequestParam(name = "size", defaultValue = "5", required = false) int size){
		Pageable pageRequest = PageRequest.of(page, size);
		Page<Empleado> empleados = empleadoService.findAll(pageRequest);
		return new ResponseEntity<>(empleados, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Empleado> consultar(@PathVariable(name = "id") int id) {
		Empleado empleado = empleadoService.findOne(id);
		if (empleado == null)
			throw new EntityNotFoundException("Empleado no encontrado con id: " + id);
		return new ResponseEntity<>(empleado, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Empleado> registrar(@Valid @RequestBody Empleado empleado) {
		Usuario usuario = usuarioService.findOne(empleado.getUsuario().getId());
		empleado.setUsuario(usuario);
		Empleado obj = empleadoService.save(empleado);
		return new ResponseEntity<>(obj, HttpStatus.CREATED);
	}
	
	@PutMapping("/actualizar")
    public ResponseEntity<Empleado> actualizar(@Valid @RequestBody Empleado empleado) {
        Empleado obj = empleadoService.findOne(empleado.getId());
        if (obj == null)
            throw new EntityNotFoundException("Empleado no encontrado con el ID: " + empleado.getId());
        Usuario usuario = usuarioService.findOne(empleado.getUsuario().getId());
        empleado.setUsuario(usuario);
        empleado = empleadoService.save(empleado);
        return new ResponseEntity<>(empleado, HttpStatus.OK);
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminar(@PathVariable(name = "id") int id) {
        Empleado obj = empleadoService.findOne(id);
        if (obj == null)
            throw new EntityNotFoundException("Empleado no encontrado con el ID: " + id);
        empleadoService.delete(id);
        return new ResponseEntity<>("Empleado eliminado exitosamente", HttpStatus.OK);
    }
}
