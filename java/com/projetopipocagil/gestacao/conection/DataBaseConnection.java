package com.projetopipocagil.gestacao.conection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//PARA OBTER E FECHAR UM CONEXAPO
public class DataBaseConnection {

	private static DataBaseConnection instance;

	private Connection connection;
	
	public static DataBaseConnection getInstance() {
		if (instance == null) {
			instance = new DataBaseConnection();
		}
		return instance;
	}

	private DataBaseConnection() {
		try {
			createConnection();
		} catch (SQLException e) {
			System.out.println("Error ao se conectar ao banco de dados: " + e.getMessage());
		}
	}

	public Connection getConnection() {
		return connection;
	}

	private void createConnection() throws SQLException {
		this.connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/gestacao", "developer", "1234");
		this.connection.setAutoCommit(true);
	}

	public void closeConnection() {
		try {
			this.connection.close();
		} catch (SQLException e) {
			System.out.println("Error ao encerrar a conexão com o banco de dados: " + e.getMessage());
		}
	}

	
}
