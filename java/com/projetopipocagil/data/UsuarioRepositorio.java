package com.projetopipocagil.data;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projetopipocagil.gestacao.models.Usuario;
import java.util.List;




@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByLogin(String email); //login do nosso usuario
    Boolean existsByEmail(String email);
    Usuario  findByEmail(String email);
    
}
