package com.jgr.service;

import java.util.List;
import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.jgr.entity.Usuario;


public interface UsuarioService extends UserDetailsService{

	public Usuario guardar(Usuario usuario);
	
	public List<Usuario> listarUsuarios();

	public Usuario findByEmail(String email);

	public Optional<Usuario> findById(Long id);

	public void update(Usuario usuario);

	public void deleteById(Long id);

	
}
