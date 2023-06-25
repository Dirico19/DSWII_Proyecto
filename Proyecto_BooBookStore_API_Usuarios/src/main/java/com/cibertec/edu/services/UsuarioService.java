package com.cibertec.edu.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cibertec.edu.entities.Usuario;
import com.cibertec.edu.repositories.UsuarioRepository;

@Service
public class UsuarioService implements IUsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Override
	@Transactional(readOnly = true)
	public List<Usuario> findAll() {
		return usuarioRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Usuario> findAll(Pageable pageable) {
		return usuarioRepository.findAll(pageable);
	}

	@Override
	@Transactional
	public void save(Usuario usuario) {
		usuarioRepository.save(usuario);
	}

	@Override
	@Transactional(readOnly = true)
	public Usuario findOne(int Id) {
		return usuarioRepository.findById(Id).orElse(null);
	}

	@Override
	@Transactional(readOnly = true)
	public Usuario findByNombre(String nombre) {		
		return usuarioRepository.findByNombre(nombre) ;
	}

}
