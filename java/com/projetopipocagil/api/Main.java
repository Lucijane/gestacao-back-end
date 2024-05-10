package com.projetopipocagil.api;

import java.sql.SQLException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.projetopipocagil.gestacao.models.Usuario;

@SpringBootApplication
class Main {
		
	public static void main(String[] args) throws SQLException {
		SpringApplication.run(Main.class, args);
		UsuarioConexao usarioconex = new UsuarioConexao();
		
		Usuario us = usarioconex.findById(0);

		if (us != null) {
			System.out.println(us);
		} else {
			System.out.println("Usuario com este id não existe us");
		}

		Usuario usuarioNovo = new Usuario(null, "Maria justo", "Bruna Maria", "Senha@4545", "lucijanecosta060@gmail.com");
		
		if (!usuarioNovo.validarEmail(usuarioNovo.getEmail())) {
	        System.out.println("Email inválido");
	        return; // sai do método, já que o email é inválido
	    }
		
		if (!usuarioNovo.validarNome(usuarioNovo.getNome())){
	        System.out.println("Nome inválido");
	        return; // sai do método, já que o nome é inválido
	    }
		
		if (!usuarioNovo.validarSenha(usuarioNovo.getSenha())) {
			System.out.println("Senha Invalida ");
			return;
		}
		if (usarioconex.create(usuarioNovo)) {
			System.out.println("Usuario criado");
		} else {
			System.out.println("Usuario não pôde ser criado");
		}

		/*Usuario usuarioUpdate = new Usuario(null, "", "", "", "");
		usarioconex.update(usuarioUpdate);
		us = usarioconex.findById(0);
		if (us != null) {
			System.out.println(us);
		} else {
			System.out.println("Usuario com este id não existe");
		}

		if (usarioconex.delete(53)) {
			System.out.println("Usuario deletado");
		} else {
			System.out.println("Usuario não pôde ser deletado");
		}*/
			
		}
}
