package br.com.wareysis.exception.user;

import br.com.wareysis.exception.ErrorResponse;

import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class UserExceptionHandler implements ExceptionMapper<UserException> {

    @Override
    public Response toResponse(UserException exception) {

        return Response.status(exception.getStatus())
                .entity(new ErrorResponse(exception.getStatus(), exception.getClass().getSimpleName(), exception.getMessage()))
                .type(MediaType.APPLICATION_JSON)
                .build();
    }
}
