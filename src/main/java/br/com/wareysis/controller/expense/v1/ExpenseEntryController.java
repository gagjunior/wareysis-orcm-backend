package br.com.wareysis.controller.expense.v1;

import org.jboss.resteasy.reactive.RestResponse;

import br.com.wareysis.domain.expense.ExpenseEntryId;
import br.com.wareysis.dto.expense.ExpenseEntryDto;
import br.com.wareysis.service.expense.ExpenseEntryService;

import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response.Status;

@Path("/v1/expense/entry")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ExpenseEntryController {

    @Inject
    ExpenseEntryService service;

    @POST
    public RestResponse<ExpenseEntryDto> create(@Valid ExpenseEntryDto dto) {

        return RestResponse.status(Status.CREATED, service.create(dto));
    }

    @PUT
    public RestResponse<ExpenseEntryDto> update(@Valid ExpenseEntryDto dto) {

        return RestResponse.ok(service.update(dto));
    }

    @DELETE
    public RestResponse<Void> delete(ExpenseEntryId id) {

        service.delete(id);

        return RestResponse.status(Status.NO_CONTENT);
    }

}
