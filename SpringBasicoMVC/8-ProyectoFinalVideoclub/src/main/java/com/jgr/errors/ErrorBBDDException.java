package com.jgr.errors;

public class ErrorBBDDException extends RuntimeException {
	
	private static final long serialVersionUID = -7422416401730360988L;

	public ErrorBBDDException(String id) {
		super("Error en acceso a BBDD: ".concat(id));
	}

}