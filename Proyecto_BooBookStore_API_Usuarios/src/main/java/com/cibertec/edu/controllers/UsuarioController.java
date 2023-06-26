package com.cibertec.edu.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cibertec.edu.entities.Usuario;
import com.cibertec.edu.services.IUsuarioService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

	@Autowired
	private IUsuarioService usuarioService;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@PostMapping("/login")
	public ResponseEntity<String> autenticacionUsuario(@Valid @RequestBody Usuario usuario) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(usuario.getNombre(), usuario.getContraseña()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		return new ResponseEntity<>("CREDENCIALES CORRECTAS", HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Usuario> registrar(@Valid @RequestBody Usuario usuario) {
		usuario.setContraseña(passwordEncoder.encode(usuario.getContraseña()));
		Usuario obj = usuarioService.save(usuario);
		return new ResponseEntity<>(obj, HttpStatus.CREATED);
	}
	
	@PutMapping("/cambiarContraseña")
	public ResponseEntity<Usuario> cambiarContraseña(
			@RequestParam(name = "actual", required = true) String actual,
			@RequestParam(name = "nueva", required = true) String nueva,
			@RequestParam(name = "id", required = true) int id) {
		Usuario obj = usuarioService.cambiarContraseña(actual, nueva, id);
		return new ResponseEntity<>(obj, HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<Page<Usuario>> listar(
			@RequestParam(name = "page", defaultValue = "0", required = false) int page,
			@RequestParam(name = "size", defaultValue = "5", required = false) int size){
		Pageable pageRequest = PageRequest.of(page, size);
		Page<Usuario> usuarios = usuarioService.findAll(pageRequest);
		return new ResponseEntity<>(usuarios, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Usuario> consultar(@PathVariable(name = "id") int id) {
		Usuario usuario = usuarioService.findOne(id);
		if (usuario == null)
			throw new EntityNotFoundException("Usuario no encontrado con id: " + id);
		return new ResponseEntity<>(usuario, HttpStatus.OK);
	}
	
	@PutMapping("/actualizar")
    public ResponseEntity<Usuario> actualizar(@Valid @RequestBody Usuario usuario) {
        Usuario obj = usuarioService.findOne(usuario.getId());
        if (obj == null)
            throw new EntityNotFoundException("Usuario no encontrado con el ID: " + usuario.getId());
        usuario.setContraseña(passwordEncoder.encode(usuario.getContraseña()));
        usuario = usuarioService.save(usuario);
        return new ResponseEntity<>(usuario, HttpStatus.OK);
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminar(@PathVariable(name = "id") int id) {
    	Usuario obj = usuarioService.findOne(id);
        if (obj == null)
            throw new EntityNotFoundException("Usuario no encontrado con el ID: " + id);
        usuarioService.delete(id);
        return new ResponseEntity<>("Usuario eliminado exitosamente", HttpStatus.OK);
    }
	
}
