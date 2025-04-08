package br.com.wareysis.exception;

import jakarta.ws.rs.core.Response;

public record ErrorResponse(
        Response.Status statusName,
        String className,
        String message
) {
}
