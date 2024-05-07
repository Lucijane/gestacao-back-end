package com.projetopipocagil.gestacao.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projetopipocagil.data.UsuarioRepositorio;
import com.projetopipocagil.gestacao.conection.ServicoExc;
import com.projetopipocagil.gestacao.models.Usuario;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;


@Service
public class ServicoUsuario {
	@Autowired
	private UsuarioRepositorio usuarioRepositorio;
	
	

	public List<Usuario> findAll() {
		return usuarioRepositorio.findAll();
	}
	
	public Usuario findById(Long usuarioId) throws ServicoExc {
		Optional<Usuario> usuario = usuarioRepositorio.findById(usuarioId);

		return usuario.orElseThrow(() -> new ServicoExc(
				"Objeto nÃo encontrado! Id: " + usuarioId + ", Tipo: " + Usuario.class.getName()));
	}
	
	@Autowired
    private Validator validator;

    public Usuario salvarUsuario(Usuario usuario) {
        Set<ConstraintViolation<Usuario>> violations = validator.validate(usuario);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
        return usuarioRepositorio.save(usuario);
}
	

	public Usuario update(Long usuarioId, Usuario usuarioAtualizado) throws ServicoExc {
		Usuario usuario = findById(usuarioId);
		usuario.setNome(usuarioAtualizado.getNome());
		usuario.setLogin(usuarioAtualizado.getLogin());
		usuario.setSenha(usuarioAtualizado.getSenha());
		usuario.setEmail(usuarioAtualizado.getEmail());
		
		return usuarioRepositorio.save(usuario);
	}

	public Usuario create(Usuario usuarioNovo) {
		usuarioNovo.setId(0);
		return usuarioRepositorio.save(usuarioNovo);
	}

	public void delete(Long usuarioId) {
		try {
			findById(usuarioId);
		} catch (ServicoExc e) {
			
		}
		usuarioRepositorio.deleteById(usuarioId);
	
    }
}