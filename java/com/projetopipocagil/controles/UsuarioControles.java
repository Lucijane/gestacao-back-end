package com.projetopipocagil.controles;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projetopipocagil.dto.UsuarioDTO;
import com.projetopipocagil.gestacao.service.ServicoUsuario;

import jakarta.validation.ConstraintValidatorContext.ConstraintViolationBuilder.NodeContextBuilder;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/usuarios")
@Validated
public class UsuarioControles {

    @Autowired
    private ServicoUsuario servicoUsuario;

    private static final Logger logger = LoggerFactory.getLogger(UsuarioControles.class);

    @GetMapping
    public List<UsuarioDTO> listarTodos() {
        logger.info("Requisição para listar todos os usuários recebida.                                                               ");
        return servicoUsuario.listarTodos();
    }

    @PostMapping
    public ResponseEntity<UsuarioDTO> inserir(@Valid @RequestBody UsuarioDTO usuario) {
        logger.info("Requisição para inserir usuário recebida: {}", usuario);
        servicoUsuario.inserir(usuario);
        return ResponseEntity.ok(usuario);
    }
    
    @PutMapping
    public UsuarioDTO alterar(@RequestBody UsuarioDTO usuario) {
        logger.info("Requisição para alterar usuário recebida: {}", usuario);
        return servicoUsuario.alterar(usuario);
    }
    
    
    @DeleteMapping("/{id}")
    public ResponseEntity<NodeContextBuilder> excluir(@PathVariable("id") Long id) {
        logger.info("Requisição para excluir usuário recebida: ID={}", id);
        servicoUsuario.excluir(id);
        return ResponseEntity.ok().body(null);
    }
    
    
}
