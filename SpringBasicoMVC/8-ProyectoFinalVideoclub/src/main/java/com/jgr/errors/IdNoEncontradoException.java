package com.jgr.errors;

public class IdNoEncontradoException extends RuntimeException {
	
	private static final long serialVersionUID = -7422416401730360988L;

	public IdNoEncontradoException(String id) {
		super("ID: ".concat(id).concat(" no existe en el sistema"));
	}

}