package br.com.wareysis.exception.expense;

import br.com.wareysis.exception.AbstractException;

import lombok.Getter;
import lombok.Setter;

import jakarta.ws.rs.core.Response;

@Getter
@Setter
public class ExpenseException extends AbstractException {

    public ExpenseException(String message, Response.Status status) {

        super(message, status);

    }
}
