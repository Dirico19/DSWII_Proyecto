package com.cibertec.edu.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.cibertec.edu.entities.Usuario;
import com.cibertec.edu.repositories.UsuarioRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class UsuarioService implements IUsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Override
	public List<Usuario> findAll() {
		return usuarioRepository.findAll();
	}

	@Override
	public Page<Usuario> findAll(Pageable pageable) {
		return usuarioRepository.findAll(pageable);
	}

	@Override
	public Usuario save(Usuario usuario) {
		return usuarioRepository.save(usuario);
	}

	@Override
	public Usuario findOne(int Id) {
		return usuarioRepository.findById(Id).orElse(null);
	}

	@Override
	public Usuario findByNombre(String nombre) {		
		return usuarioRepository.findByNombre(nombre) ;
	}

	@Override
	public Usuario cambiarContraseña(String actual, String nueva, int id) {
		Usuario usuario = usuarioRepository.findById(id).orElseThrow(
				() -> new EntityNotFoundException("Usuario no encontrado con el id: " + id));
		if (passwordEncoder.matches(actual, usuario.getContraseña())) {
			if (nueva.isBlank() || nueva.isEmpty() || !nueva.matches("^.{6,}$"))
				throw new IllegalArgumentException("La nueva contraseña debe contener almenos 6 caracteres.");
			usuario.setContraseña(passwordEncoder.encode(nueva));
			return usuarioRepository.save(usuario);
		} else {
			throw new IllegalArgumentException("La contraseña actual es incorrecta.");
		}
	}

	@Override
	public void delete(int id) {
		usuarioRepository.deleteById(id);
	}

}
