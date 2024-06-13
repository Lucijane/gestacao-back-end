package com.projetopipocagil.gestacao.service;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.projetopipocagil.data.UsuarioRepositorio;
import com.projetopipocagil.dto.UsuarioDTO;
import com.projetopipocagil.gestacao.models.Usuario;

@Service
public class ServicoUsuario {

	private static final Logger logger = LoggerFactory.getLogger(ServicoUsuario.class);

	@Autowired
	private UsuarioRepositorio usuarioRepositorio;

	public List<UsuarioDTO> listarTodos() {
		logger.info("Método listarTodos() chamado.");

		List<Usuario> usuarios = usuarioRepositorio.findAll();
		logger.info("Total de usuários encontrados: {}", usuarios.size());

		List<UsuarioDTO> requestUsuarios = usuarios.stream().map(UsuarioDTO::new).collect(Collectors.toList());

		logger.info("Total de RequestUsuario mapeados: {}", requestUsuarios.size());

		return requestUsuarios;
	}

	public void inserir(UsuarioDTO usuario) {
		Usuario usuarioEntidade = new Usuario(usuario);
		usuarioRepositorio.save(usuarioEntidade);
	}

	public UsuarioDTO alterar(UsuarioDTO usuario) {
		Usuario usuarioEntidade = new Usuario(usuario);
		return new UsuarioDTO(usuarioRepositorio.save(usuarioEntidade));
	}

	public void excluir(Long id) {
		Usuario usuario = usuarioRepositorio.findById(id).get();
		usuarioRepositorio.delete(usuario);
	}

	public UsuarioDTO buscarId(Long id) {
		return new UsuarioDTO(usuarioRepositorio.findById(id).get());
	}

	

}