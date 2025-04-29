package br.com.wareysis.controller.expense.v1;

import org.jboss.resteasy.reactive.RestResponse;
import org.jboss.resteasy.reactive.RestResponse.Status;

import br.com.wareysis.dto.expense.ExpenseInstallmentDto;
import br.com.wareysis.service.expense.ExpenseInstallmentService;

import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/v1/expense/installment")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ExpenseInstallmenteController {

    @Inject
    ExpenseInstallmentService service;

    @POST
    public RestResponse<ExpenseInstallmentDto> create(@Valid ExpenseInstallmentDto installmentDto) {

        return RestResponse.status(Status.CREATED, service.create(installmentDto));
    }

    @PUT
    public RestResponse<ExpenseInstallmentDto> update(@Valid ExpenseInstallmentDto installmentDto) {

        return RestResponse.status(Status.OK, service.update(installmentDto));
    }

}
