package br.com.wareysis.exception.category;

import lombok.Getter;
import lombok.Setter;

import jakarta.ws.rs.core.Response;

@Getter
@Setter
public class CategoryException extends RuntimeException {

    private final Response.Status status;

    public CategoryException(String message, Response.Status status) {

        super(message);
        this.status = status;
    }
}
