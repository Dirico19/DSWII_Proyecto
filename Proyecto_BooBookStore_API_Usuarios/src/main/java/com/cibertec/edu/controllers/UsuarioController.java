package com.cibertec.edu.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cibertec.edu.entities.Usuario;
import com.cibertec.edu.services.IUsuarioService;

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
	
}
