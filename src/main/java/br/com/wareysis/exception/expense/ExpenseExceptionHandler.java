package br.com.wareysis.exception.expense;

import br.com.wareysis.exception.AbstractExceptionHandler;

import jakarta.ws.rs.ext.Provider;

@Provider
public class ExpenseExceptionHandler extends AbstractExceptionHandler<ExpenseException> {

}
