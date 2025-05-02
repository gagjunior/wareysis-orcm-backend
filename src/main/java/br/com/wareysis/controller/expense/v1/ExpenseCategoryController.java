package br.com.wareysis.controller.expense.v1;

import java.util.List;

import org.jboss.resteasy.reactive.RestResponse;
import org.jboss.resteasy.reactive.RestResponse.Status;

import br.com.wareysis.dto.category.CategoryDto;
import br.com.wareysis.service.expense.ExpenseCategoryService;

import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;

@Path("/v1/expense/category")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ExpenseCategoryController {

    @Inject
    ExpenseCategoryService service;

    @POST
    public RestResponse<CategoryDto> create(@Valid CategoryDto categoryDto, @Context ContainerRequestContext ctx) {

        return RestResponse.status(Status.CREATED, service.create(categoryDto, ctx));
    }

    @PUT
    public RestResponse<CategoryDto> update(@Valid CategoryDto categoryDto, @Context ContainerRequestContext ctx) {

        return RestResponse.ok(service.update(categoryDto, ctx));
    }

    @DELETE
    @Path("/{name}")
    public RestResponse<Void> delete(@PathParam("name") String name, @Context ContainerRequestContext ctx) {

        service.delete(name, ctx);

        return RestResponse.status(Status.NO_CONTENT);
    }

    @GET
    public RestResponse<List<CategoryDto>> findAllCategories(@Context ContainerRequestContext ctx) {

        return RestResponse.ok(service.findAllByUserId(ctx));
    }

    @GET
    @Path("/{name}")
    public RestResponse<List<CategoryDto>> findAllByName(@PathParam("name") String name, @Context ContainerRequestContext ctx) {

        return RestResponse.ok(service.findAllByName(name, ctx));

    }

}
