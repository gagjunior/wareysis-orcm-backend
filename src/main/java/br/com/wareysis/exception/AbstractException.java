package br.com.wareysis.exception;

import lombok.Getter;
import lombok.Setter;

import jakarta.ws.rs.core.Response;

@Getter
@Setter
public class AbstractException extends RuntimeException {

    private final Response.Status status;

    public AbstractException(String message, Response.Status status) {

        super(message);
        this.status = status;
    }
}
