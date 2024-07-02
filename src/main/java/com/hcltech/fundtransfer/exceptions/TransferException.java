package com.hcltech.fundtransfer.exceptions;

import org.springframework.security.core.AuthenticationException;

public class TransferException extends AuthenticationException {

	private static final long serialVersionUID = 1L;

	public TransferException(String mensagem) {
		super(mensagem);
	}
	
}
