package br.com.wareysis.exception.user;

import lombok.Getter;
import lombok.Setter;

import jakarta.ws.rs.core.Response;

@Getter
@Setter
public class UserException extends RuntimeException {

    private final Response.Status status;

    public UserException(String message, Response.Status status) {

        super(message);
        this.status = status;

    }
}
