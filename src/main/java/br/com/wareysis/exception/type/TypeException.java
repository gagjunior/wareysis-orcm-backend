package br.com.wareysis.exception.type;

import lombok.Getter;
import lombok.Setter;

import jakarta.ws.rs.core.Response;

@Getter
@Setter
public class TypeException extends RuntimeException {

    private final Response.Status status;

    public TypeException(String message, Response.Status status) {

        super(message);
        this.status = status;
    }
}
