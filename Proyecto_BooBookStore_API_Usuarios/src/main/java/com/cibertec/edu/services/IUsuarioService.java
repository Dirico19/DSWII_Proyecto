package com.cibertec.edu.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.cibertec.edu.entities.Usuario;

public interface IUsuarioService {
	
	public List<Usuario> findAll();
	
	public Page<Usuario> findAll(Pageable pageable);
	
	public Usuario save(Usuario usuario);
	
	public Usuario findOne(int Id);
	
	public Usuario findByNombre(String nombre);
	
	public Usuario cambiarContraseña(String actual, String nueva, int id);
	
	public void delete(int id);
}
