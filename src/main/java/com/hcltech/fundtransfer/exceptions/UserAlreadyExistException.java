package com.hcltech.fundtransfer.exceptions;

import org.springframework.security.core.AuthenticationException;

public class UserAlreadyExistException extends AuthenticationException {

	private static final long serialVersionUID = 1L;

	public UserAlreadyExistException(String mensagem) {
		super(mensagem);
	}
	
}
