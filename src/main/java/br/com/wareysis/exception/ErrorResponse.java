package br.com.wareysis.exception;

import java.time.LocalDateTime;

import lombok.Builder;

import jakarta.ws.rs.core.Response;

@Builder
public record ErrorResponse(
        Integer statusCode,
        Response.Status statusName,
        String message,
        LocalDateTime timestamp
) {

}
