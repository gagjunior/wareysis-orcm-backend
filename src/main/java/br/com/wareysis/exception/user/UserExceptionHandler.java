package br.com.wareysis.exception.user;

import br.com.wareysis.exception.AbstractExceptionHandler;

import jakarta.ws.rs.ext.Provider;

@Provider
public class UserExceptionHandler extends AbstractExceptionHandler<UserException> {

}
