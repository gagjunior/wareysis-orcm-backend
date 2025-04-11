package br.com.wareysis.controller.expense.v1;

import br.com.wareysis.domain.expense.ExpenseEntry;
import br.com.wareysis.service.expense.ExpenseEntryService;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

@ApplicationScoped
public class ExpenseEntryController implements ExpenseApi {

    @Inject
    ExpenseEntryService expenseEntryService;

    @POST
    @Transactional
    @Path("/entry")
    public Response create(ExpenseEntry expenseEntry) {

        expenseEntryService.create(expenseEntry);
        return Response.status(Response.Status.CREATED).entity(expenseEntry).build();
    }

}
