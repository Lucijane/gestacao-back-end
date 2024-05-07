package com.projetopipocagil.gestacao.models;

import java.io.Serializable;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.projetopipocagil.gestacao.dto.RequestUsuario;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity                                                                                         //PARA CRIAR UMA TABELA
@Table(name = "G_USUARIOS")                                                                      //DIGO QUAL NOME DE TABELA PARA ESTE OBJETO USUARIO NO BD@Get
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Usuario implements Serializable {
	
	private static final long serialVersionUID = 1L;                                                                                      //CRIANDO ATRIBUTOS DA CLASSE
	@Id                                                                                        //CHAVE PRIMARIA , AQUI É UM MAPEAMENTO ATRAVEZ DO JPA
	@GeneratedValue(strategy = GenerationType.IDENTITY)                                          /*ESTRATEGIA QUE ELE VAI                                                                                     PREENCHER ESSE MEU CAMPO ID, PASSANDO UMA ESTRATEGIA, COMO SE ELE PEGASSE UM AUTOINCREMENTE DO BD E FIZESSE UM NUMERO SEQUENCIAL*/
	private long id;
	
	@Column(nullable = false, updatable = false)                                                                            // AQUI EU QUERO QUE O NOME SEJA DE PREENCHIMENTO OBRIGATORIO, ENTAO ESE CAMPO NAO PODE SER NUMERO.
	private String nome;
	
	@Column(nullable = false, unique = true)
	private String login;
	
	@Column(nullable = false)
	@NotEmpty                                          //garante que o campo não esteja vazio
	private String senha;
	
	@Column(nullable = false, unique = true)
	@NotEmpty                                           //garante que o campo não esteja vazio
	@Email                                              //valida se o campo email possui um formato de e-mail válido. 
	private String email;
	
	public long getId() {
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

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	public Usuario(Long id, String nome, String login,String senha, String email) {
		super();
		this.id = (id != null) ? id : 0L;
		this.nome = nome;
		this.login = login;
		this.senha = senha;
		this.email = email;
	}

	public Usuario(RequestUsuario requestUsuario ) {
		 this.nome = (requestUsuario.nome() != null) ? requestUsuario.nome() : "";
		    this.login = (requestUsuario.login() != null) ? requestUsuario.login() : "";
		    this.senha = (requestUsuario.senha() != null) ? requestUsuario.senha() : "";
		    this.email = (requestUsuario.email() != null) ? requestUsuario.email() : "";
		/*this.nome = requestUsuario.nome();
		this.login = requestUsuario.login();
		this.senha = requestUsuario.senha();
		this.email = requestUsuario.email();*/
	}

	public Usuario() {
		
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
	
	
	
	@Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", login='" + login + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
	
	
	/*UMA REGRA DE NEGOCIO: NAO POSSO TER MAIS DE UM LOGIN COM O MESMO USUARIO, NO JPA TEM UMA ANOTACAO EU INDICO QUE O LOGIN SERÁ UNICO,
	SE TENTAR COLCOAR UM LOGIN QUE JÁ EXISTE ELE VAI ACUSAR UM ERRO */
	
}
