package br.com.wareysis.controller.user.v1;

import br.com.wareysis.dto.user.CreateUserDto;
import br.com.wareysis.dto.user.UpdateUserDto;
import br.com.wareysis.service.user.UserService;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;

public class UserController implements UserApi {

    @Inject
    UserService userService;

    @POST
    @Transactional
    public Response createUser(@Valid CreateUserDto createUserDto) {

        return Response.status(Response.Status.CREATED).entity(userService.createUser(createUserDto)).build();

    }

    @PUT
    @Path("/{userId}")
    @Transactional
    public Response updateUser(@PathParam("userId") Long userId, @Valid UpdateUserDto updateUserDto) {

        return Response.status(Response.Status.OK).entity(userService.updateUser(userId, updateUserDto)).build();

    }

}
