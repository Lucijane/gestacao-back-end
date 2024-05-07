package com.projetopipocagil.api;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

import com.projetopipocagil.gestacao.conection.DataBaseConnection;
import com.projetopipocagil.gestacao.models.Usuario;

public class UsuarioConexao {

	private DataBaseConnection conn;

	public UsuarioConexao() {
		this.conn = DataBaseConnection.getInstance();
	}

	// Método para buscar todos OS usuários na tabela g_usuarios
	public List<Usuario> findAll() throws SQLException {
		List<Usuario> usuarios = new ArrayList<>(); // Cria uma lista vazia para armazenar os usuários
		PreparedStatement preparedStatement = this.conn.getConnection().prepareStatement("select * from g_usuarios;");// Prepara
																														// a
																														// consulta
																														// SQL
		ResultSet resultSet = preparedStatement.executeQuery();// Executa a consulta e obtém o resultado

		while (resultSet.next()) {// Itera sobre cada linha do resultado
			Usuario us = new Usuario(); // Cria um objeto Usuario para armazenar os dados
			us.setId(resultSet.getInt("id")); // Define o ID do usuário
			us.setNome(resultSet.getString("nome"));
			us.setLogin(resultSet.getString("login"));
			us.setSenha(resultSet.getString("senha"));
			us.setEmail(resultSet.getString("email"));
			usuarios.add(us);// Adiciona o usuário à lista
		}
		return usuarios;// Retorna a lista de usuários
	}

	public Usuario findById(int id) throws SQLException {
		Usuario us = null;
		PreparedStatement preparedStatement = this.conn.getConnection()
				.prepareStatement("select * from G_USUARIOS where id = ?;");
		preparedStatement.setInt(1, id);
		ResultSet resultSet = preparedStatement.executeQuery();

		if (resultSet.next()) {
			us = new Usuario();
			us.setId(resultSet.getInt("id"));
			us.setNome(resultSet.getString("nome"));
			us.setLogin(resultSet.getString("login"));
			us.setSenha(resultSet.getString("senha"));
			us.setEmail(resultSet.getString("email"));
		}

		return us;
	}

	public boolean create(Usuario usuarioNovo) throws SQLException {
		boolean isCreated = false;
		String createStringSQL = "INSERT INTO G_USUARIOS (nome,login,senha,email) VALUES(?,?,?,?);";

		PreparedStatement preparedStatement = this.conn.getConnection().prepareStatement(createStringSQL);
		preparedStatement.setString(1, usuarioNovo.getNome());
		preparedStatement.setString(2, usuarioNovo.getLogin());
		preparedStatement.setString(3, usuarioNovo.getSenha());
		preparedStatement.setString(4, usuarioNovo.getEmail());

		preparedStatement.execute();

		if (preparedStatement.getUpdateCount() == 1) {
			isCreated = true;
		}

		return isCreated;
	}

	public boolean update(Usuario usuarioUpdate) throws SQLException {
		boolean isUpdated = false;
		String updateStringSQL = "UPDATE G_USUARIOS\n" + "SET nome=?, " + " login=?, " + "senha=?," + "email=?,"
				+ " WHERE id=? ;";

		PreparedStatement preparedStatement = this.conn.getConnection().prepareStatement(updateStringSQL);
		preparedStatement.setString(1, usuarioUpdate.getNome());
		preparedStatement.setString(2, usuarioUpdate.getLogin());
		preparedStatement.setString(3, usuarioUpdate.getSenha());
		preparedStatement.setString(4, usuarioUpdate.getEmail());
		preparedStatement.execute();

		if (preparedStatement.getUpdateCount() == 1) {
			isUpdated = true;
		}

		return isUpdated;
	}

	/*
	 * public boolean delete(int id) throws SQLException { boolean isDeleted =
	 * false; String deleteStringSQL = "DELETE FROM G_USUARIOS WHERE id=?;";
	 * 
	 * PreparedStatement preparedStatement =
	 * this.conn.getConnection().prepareStatement(deleteStringSQL);
	 * preparedStatement.setInt(1, id); preparedStatement.execute();
	 * 
	 * if (preparedStatement.getUpdateCount() == 1) { isDeleted = true; }
	 * 
	 * return isDeleted;
	 * 
	 * 
	 * }
	 */
	public boolean delete(int id) {
		boolean isDeleted = false;
		String deleteStringSQL = "DELETE FROM G_USUARIOS WHERE id=?;";

		try {
			PreparedStatement preparedStatement = this.conn.getConnection().prepareStatement(deleteStringSQL);
			preparedStatement.setInt(1, id);
			preparedStatement.execute();

			if (preparedStatement.getUpdateCount() == 1) {
				isDeleted = true;
			}
		} catch (SQLException e) {
			if (e instanceof SQLIntegrityConstraintViolationException) {
				// Tratamento específico para violação de chave única
				System.err.println("Erro de violação de chave única: " + e.getMessage());
				// Você pode retornar false, lançar uma exceção personalizada ou tomar outra
				// ação adequada aqui
			} else {
				// Outro tipo de exceção SQL
				System.err.println("Erro SQL: " + e.getMessage());
				// Você pode retornar false, lançar uma exceção personalizada ou tomar outra
				// ação adequada aqui
			}
		}

		return isDeleted;
	}
}
