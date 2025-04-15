package br.com.wareysis.exception.type;

import br.com.wareysis.exception.AbstractException;

import lombok.Getter;
import lombok.Setter;

import jakarta.ws.rs.core.Response;

@Getter
@Setter
public class TypeException extends AbstractException {

    public TypeException(String message, Response.Status status) {

        super(message, status);

    }
}
