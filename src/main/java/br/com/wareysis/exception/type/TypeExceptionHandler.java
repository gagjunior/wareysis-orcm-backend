package br.com.wareysis.exception.type;

import br.com.wareysis.exception.ErrorResponse;

import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class TypeExceptionHandler implements ExceptionMapper<TypeException> {

    @Override
    public Response toResponse(TypeException exception) {

        return Response.status(exception.getStatus())
                .entity(new ErrorResponse(exception.getStatus(), exception.getClass().getSimpleName(), exception.getMessage()))
                .type(MediaType.APPLICATION_JSON)
                .build();
    }
}
