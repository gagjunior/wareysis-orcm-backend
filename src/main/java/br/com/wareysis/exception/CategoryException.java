package br.com.wareysis.exception;

import jakarta.ws.rs.core.Response;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryException extends RuntimeException {

    private Response.Status status;

    public CategoryException(String message, Response.Status status) {
        super(message);
        this.status = status;
    }
}
