package br.com.wareysis.controller.income.v1;

import br.com.wareysis.domain.category.CategoryId;
import br.com.wareysis.dto.CategoryDto;
import br.com.wareysis.service.income.IncomeCategoryService;
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

public class IncomeCategoryController implements IncomeApi {

    @Inject
    IncomeCategoryService categoryService;

    @POST
    @Transactional
    @Path("/category")
    public Response create(@Valid CategoryDto categoryDto) {

        return Response.status(Response.Status.CREATED).entity(categoryService.create(categoryDto)).build();
    }

    @DELETE
    @Transactional
    @Path("/category/{userId}/{name}")
    public Response delete(@PathParam("userId") Long userId, @PathParam("name") String name) {

        CategoryId categoryId = new CategoryId(userId, name);

        categoryService.delete(categoryId);

        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @PUT
    @Path("/category")
    @Transactional
    public Response update(@Valid CategoryDto categoryDto) {

        categoryService.update(categoryDto);
        return Response.status(Response.Status.NO_CONTENT).build();
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
