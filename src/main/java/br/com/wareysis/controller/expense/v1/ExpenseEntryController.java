package br.com.wareysis.controller.expense.v1;

import br.com.wareysis.dto.expense.ExpenseEntryDto;
import br.com.wareysis.service.expense.ExpenseEntryService;

import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/v1/expense/entry")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ExpenseEntryController {

    @Inject
    ExpenseEntryService expenseEntryService;

    @POST
    public Response create(@Valid ExpenseEntryDto dto) {

        return Response.status(Response.Status.CREATED).entity(expenseEntryService.create(dto)).build();
    }

}
