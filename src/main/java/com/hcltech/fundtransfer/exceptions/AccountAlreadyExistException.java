package com.hcltech.fundtransfer.exceptions;

import org.springframework.security.core.AuthenticationException;

public class AccountAlreadyExistException extends AuthenticationException {

	private static final long serialVersionUID = 1L;

	public AccountAlreadyExistException(String mensagem) {
		super(mensagem);
	}
	
}
