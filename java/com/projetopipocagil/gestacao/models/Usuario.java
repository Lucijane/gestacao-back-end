package com.projetopipocagil.gestacao.models;

import java.io.Serializable;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.projetopipocagil.dto.UsuarioDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.EqualsAndHashCode;

@Table(name = "g_usuarios")
@Entity(name = "g_usuarios")
@EqualsAndHashCode(of = "id")
public class Usuario implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, updatable = false)
	@Size(min = 5, max = 30, message = "O Nome deve conter entre 5 a 30 caracteres")
	@NotBlank(message = "O nome não pode ser vazio")
	private String nome;

	@Column(nullable = false, unique = true)
	@NotEmpty(message = "O login é obrigatório")
	private String login;

	@Column(nullable = false)
	@NotEmpty(message = "A senha é obrigatória")
	@Size(min = 8, max = 20, message = "A senha deve ter entre 8 e 20 caracteres")
	private String senha;

	@Column(nullable = false, unique = true)
	@NotEmpty(message = "O e-mail é obrigatório")
	@Email(message = "E-mail inválido") // valida se o campo email possui um formato de e-mail válido.
	private String email;

	

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getLogin() {
		return login;
	}

	public String getSenha() {
		return senha;
	}

	public String getEmail() {
		return email;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setNome(String nome) { // a validação será realizada antes de atribuir o valor ao campo correspondente.
		if (validarNome(nome)) {
			this.nome = nome;
		} else {
			throw new IllegalArgumentException("Nome inválido");
		}
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public void setSenha(String senha) {
		if (validarSenha(senha)) {
			this.senha = senha;
		} else {
			throw new IllegalArgumentException("Senha inválida");
		}
	}

	public void setEmail(String email) {
		if (validarEmail(email)) {
			this.email = email;
		} else {
			throw new IllegalArgumentException("E-mail inválido");
		}
	}

	public Usuario() {
	}

	public Usuario(String email, String senha) {
		this.email = email;
		this.senha = senha;
	}

	/*public Usuario(String nome, String login, String senha, String email) {

		this.nome = nome;
		this.login = login;
		this.senha = senha;
		this.email = email;

	}*/

	public Usuario(String nome, String login, String senha, String email) {
		super();
		this.id = (id != null) ? id : 0L;
		this.nome = nome;
		this.login = login;
		this.senha = senha;
		this.email = email;

	}

	public Usuario(UsuarioDTO usuarioDto) {
		this.setEmail(usuarioDto.getEmail());
		this.setNome(usuarioDto.getNome());
		this.setSenha(usuarioDto.getSenha());
		this.setLogin(usuarioDto.getLogin());
	}

	public boolean validarEmail(String email) {
		if (email == null || email.isEmpty()) {
			return false;
		}
		String regex = "^(.+)@(.+)$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}

	public boolean validarNome(String nome) {
		// Verifica se o nome não é nulo
		if (nome == null) {
			return false;
		}

		// Verifica se o nome contém pelo menos dois nomes
		String[] partesNome = nome.split(" ");
		if (partesNome.length < 2) {
			return false;
		}

		// Verifica se o nome não contém caracteres especiais ou números
		if (!nome.matches("[a-zA-ZÀ-ú\\s]+")) {
			return false;
		}

		// Verifica se o nome tem o comprimento mínimo e máximo
		int comprimentoMinimo = 3; // Exemplo: mínimo de 3 caracteres
		int comprimentoMaximo = 50; // Exemplo: máximo de 50 caracteres
		if (nome.length() < comprimentoMinimo || nome.length() > comprimentoMaximo) {
			return false;
		}
		return true;
	}

	public boolean validarSenha(String senha) {
		// Verifica se a senha não é nula
		if (senha == null) {
			return false;
		}

		// Verifica se a senha contém pelo menos uma letra maiúscula
		if (!senha.matches(".*[A-Z].*")) {
			return false;
		}

		// Verifica se a senha contém pelo menos um número
		if (!senha.matches(".*\\d.*")) {
			return false;
		}

		// Verifica se a senha contém pelo menos um caractere especial
		if (!senha.matches(".*[!@#$%^&*()-+=?].*")) {
			return false;
		}

		// Verifica se a senha tem o comprimento mínimo e máximo
		int comprimentoMinimo = 8; // Exemplo: mínimo de 8 caracteres
		int comprimentoMaximo = 20; // Exemplo: máximo de 20 caracteres
		if (senha.length() < comprimentoMinimo || senha.length() > comprimentoMaximo) {
			return false;
		}

		return true;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		return Objects.equals(id, other.id);
	}

}
