package br.com.wareysis.exception;

import java.time.LocalDateTime;

import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;

public class AbstractExceptionHandler<T extends AbstractException> implements ExceptionMapper<T> {

    @Override
    public Response toResponse(T exception) {

        return Response.status(exception.getStatus())
                .entity(ErrorResponse.builder()
                        .statusCode(exception.getStatus().getStatusCode())
                        .statusName(exception.getStatus())
                        .message(exception.getMessage())
                        .timestamp(LocalDateTime.now())
                        .build())
                .type(MediaType.APPLICATION_JSON)
                .build();
    }
}
