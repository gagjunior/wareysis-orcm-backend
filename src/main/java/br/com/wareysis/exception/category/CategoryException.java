package br.com.wareysis.exception.category;

import br.com.wareysis.exception.AbstractException;

import lombok.Getter;
import lombok.Setter;

import jakarta.ws.rs.core.Response;

@Getter
@Setter
public class CategoryException extends AbstractException {

    public CategoryException(String message, Response.Status status) {

        super(message, status);

    }
}
