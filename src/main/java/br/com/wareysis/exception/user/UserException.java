package br.com.wareysis.exception.user;

import br.com.wareysis.exception.AbstractException;

import lombok.Getter;
import lombok.Setter;

import jakarta.ws.rs.core.Response;

@Getter
@Setter
public class UserException extends AbstractException {

    public UserException(String message, Response.Status status) {

        super(message, status);

    }
}
