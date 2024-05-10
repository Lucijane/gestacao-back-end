package com.projetopipocagil.gestacao.conection;

import java.io.Serial;

public class ServicoExc extends Exception {
	private static final long serialVersionUID = 1L;

	public ServicoExc(String message, Throwable cause) {
		super(message, cause);
	}

	public ServicoExc(String message) {
		super(message);
	}
}

