package com.cibertec.edu.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.cibertec.edu.entities.Usuario;
import com.cibertec.edu.entities.UsuarioDetalles;
import com.cibertec.edu.repositories.UsuarioRepository;

public class UsuarioDetallesService implements UserDetailsService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = usuarioRepository.findByNombre(username);
		
		if (usuario == null)
			throw new UsernameNotFoundException("El usuario no existe");
		
		return new UsuarioDetalles(usuario);
	}

}
