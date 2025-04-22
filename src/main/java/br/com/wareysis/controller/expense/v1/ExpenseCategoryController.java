package br.com.wareysis.controller.expense.v1;

import java.util.List;

import org.jboss.resteasy.reactive.RestResponse;
import org.jboss.resteasy.reactive.RestResponse.Status;

import br.com.wareysis.domain.category.CategoryId;
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
import jakarta.ws.rs.core.MediaType;

@Path("/v1/expense/category")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ExpenseCategoryController {

    @Inject
    ExpenseCategoryService service;

    @POST
    public RestResponse<CategoryDto> create(@Valid CategoryDto categoryDto) {

        return RestResponse.status(Status.CREATED, service.create(categoryDto));
    }

    @PUT
    public RestResponse<CategoryDto> update(@Valid CategoryDto categoryDto) {

        return RestResponse.ok(service.update(categoryDto));
    }

    @DELETE
    @Path("/{userId}/{name}")
    public RestResponse<Void> delete(@PathParam("userId") Long userId, @PathParam("name") String name) {

        CategoryId categoryId = new CategoryId(userId, name);

        service.delete(categoryId);

        return RestResponse.status(Status.NO_CONTENT);
    }

    @GET
    @Path("/{userId}")
    public RestResponse<List<CategoryDto>> findAllCategories(@PathParam("userId") Long userId) {

        return RestResponse.ok(service.findAllByUserId(userId));
    }

    @GET
    @Path("/{userId}/{name}")
    public RestResponse<List<CategoryDto>> findAllByName(@PathParam("userId") Long userId, @PathParam("name") String name) {

        return RestResponse.ok(service.findAllByName(new CategoryId(userId, name)));

    }

}
