package com.jgr.service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.jgr.entity.Rol;
import com.jgr.entity.Usuario;
import com.jgr.repository.UsuarioRepository;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	private UsuarioRepository usuarioRepositorio;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	public UsuarioServiceImpl(UsuarioRepository usuarioRepositorio) {
		super();
		this.usuarioRepositorio = usuarioRepositorio;
	}

	@Override
	public Usuario guardar(Usuario usuario) {

		Usuario usuarioDB = usuarioRepositorio.findByEmail(usuario.getEmail());

		// si NO existe un usuario con ese email se guarda
		if (usuarioDB == null) {
			usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
			usuario = usuarioRepositorio.save(usuario);
		} else {
			usuario = null;
		}

		return usuario;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = usuarioRepositorio.findByEmail(username);
		if (usuario == null) {
			throw new UsernameNotFoundException("Usuario o password inválidos");
		}
		return new User(usuario.getEmail(), usuario.getPassword(), mapearAutoridadesRoles(usuario.getRoles()));
	}

	private Collection<? extends GrantedAuthority> mapearAutoridadesRoles(Collection<Rol> roles) {
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getNombre())).collect(Collectors.toList());
	}

	@Override
	public List<Usuario> listarUsuarios() {
		return usuarioRepositorio.findAll();
	}

	@Override
	public Usuario findByEmail(String email) {

		return usuarioRepositorio.findByEmail(email);
	}

	@Override
	public Optional<Usuario> findById(Long id) {
		return usuarioRepositorio.findById(id);
	}

	@Override
	public void update(Usuario usuario) {

		Usuario usuarioDB = usuarioRepositorio.findByEmail(usuario.getEmail());

		// si existe el usuario se actualiza
		if (usuarioDB != null) 
		{
			// si se ha cambiado la contraseña 
			// 
			if (!usuario.getPassword().isEmpty())
			{
				// se toma la nueva
				usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
			}			
			else {
				// se mantiene la que tenía
				usuario.setPassword(usuarioDB.getPassword());
			}
			usuario = usuarioRepositorio.save(usuario);
		}
	}

	@Override
	public void deleteById(Long id) {
		usuarioRepositorio.deleteById(id);
		
	}

}
