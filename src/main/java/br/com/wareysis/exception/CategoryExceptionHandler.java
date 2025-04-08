package br.com.wareysis.exception;

import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class CategoryExceptionHandler implements ExceptionMapper<CategoryException> {
    @Override
    public Response toResponse(CategoryException exception) {
        return Response.status(exception.getStatus())
                .entity(new ErrorResponse(exception.getStatus(), exception.getClass().getSimpleName(), exception.getMessage()))
                .type(MediaType.APPLICATION_JSON)
                .build();
    }
}
