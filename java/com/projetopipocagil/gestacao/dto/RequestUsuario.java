package com.projetopipocagil.gestacao.dto;

import java.util.List;

import com.projetopipocagil.gestacao.models.Usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


public record RequestUsuario(
		Long id,
		@Size(min = 5, max = 30, message = "O Nome deve conter entre 5 a 30 caracteres")
	    @NotBlank(message = "O nome não pode ser vazio")
	    @NotNull String nome,
		@NotEmpty (message = "O login é obrigatório") String login,
		@NotEmpty (message = "A senha é obrigatória") @Size(min = 8, max = 20, message = "A senha deve ter entre 8 e 20 caracteres") String senha,
		@NotEmpty (message = "O e-mail é obrigatório") @Email(message = "E-mail inválido") String email) {

}
