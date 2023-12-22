package com.example.demo.exception;

import java.io.Serial;

import javax.naming.AuthenticationException;

public class UserAlreadyExistAuthenticationException extends AuthenticationException {
	
	/**
	 * 
	 */
	@Serial
	private static final long serialVersionUID = 1L;

	public UserAlreadyExistAuthenticationException(final String msg) {
        super(msg);
    }
}
