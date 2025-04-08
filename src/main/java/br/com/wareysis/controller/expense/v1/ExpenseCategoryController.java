package br.com.wareysis.controller.expense.v1;

import br.com.wareysis.domain.category.CategoryId;
import br.com.wareysis.dto.CategoryRequestDto;
import br.com.wareysis.service.expense.ExpenseCategoryService;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;
import org.jboss.resteasy.reactive.RestResponse.Status;

public class ExpenseCategoryController implements ExpenseApi {

    @Inject
    ExpenseCategoryService categoryService;

    @POST
    @Transactional
    @Path("/category")
    public Response create(@Valid CategoryRequestDto categoryRequestDto) {

        return Response.status(Status.CREATED).entity(categoryService.create(categoryRequestDto)).build();
    }

    @DELETE
    @Transactional
    @Path("/category/{userId}/{name}")
    public Response delete(@PathParam("userId") Long userId, @PathParam("name") String name) {

        CategoryId categoryId = new CategoryId(userId, name);

        categoryService.delete(categoryId);

        return Response.status(Status.NO_CONTENT).build();
    }

    @PUT
    @Path("/category")
    @Transactional
    public Response update(@Valid CategoryRequestDto categoryRequestDto) {

        categoryService.update(categoryRequestDto);
        return Response.status(Status.NO_CONTENT).build();
    }

    @GET
    @Path("/category/{userId}")
    public Response findAllCategories(Long userId) {

        return Response.ok(categoryService.findAllByUserId(userId)).build();
    }

    @GET
    @Path("/category/{userId}/{name}")
    public Response findAllByName(@PathParam("userId") Long userId, @PathParam("name") String name) {

        return Response.ok(categoryService.findAllByName(new CategoryId(userId, name))).build();

    }

}
