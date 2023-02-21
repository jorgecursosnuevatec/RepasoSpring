package com.aepi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aepi.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

}
