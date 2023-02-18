package com.jgr.errors;

public class GeneroExisteException extends RuntimeException {
	
	private static final long serialVersionUID = 4267497536252617469L;

	public GeneroExisteException(String genero) {
		super("Genero : ".concat(genero).concat(" ya existe en el sistema"));
	}

}