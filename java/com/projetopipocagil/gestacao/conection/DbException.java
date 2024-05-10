package com.projetopipocagil.gestacao.conection;

public class DbException extends RuntimeException{
   
	private static final long serialVersionUID = 1L;// NUMERO DE VERSAO PADRAO
	//UMA EXCECAO MINHA PERSONALIZADA PARA ACESSO AOS DADOS
	public DbException(String msg) {
		//VOU TRANSMITIR PARA A SUPER CLASSE RUNTIME
		super(msg);
	}
}
