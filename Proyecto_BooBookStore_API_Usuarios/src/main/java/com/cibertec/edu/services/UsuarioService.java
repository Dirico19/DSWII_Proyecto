package com.cibertec.edu.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.cibertec.edu.entities.Usuario;
import com.cibertec.edu.repositories.UsuarioRepository;

@Service
public class UsuarioService implements IUsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
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

}
