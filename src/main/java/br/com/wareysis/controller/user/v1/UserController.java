package br.com.wareysis.controller.user.v1;

import org.jboss.resteasy.reactive.RestResponse;
import org.jboss.resteasy.reactive.RestResponse.Status;

import br.com.wareysis.domain.user.User;
import br.com.wareysis.dto.user.CreateUserDto;
import br.com.wareysis.dto.user.UpdateUserDto;
import br.com.wareysis.service.user.UserService;

import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/v1/user")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserController {

    @Inject
    UserService userService;

    @POST
    public RestResponse<User> createUser(@Valid CreateUserDto createUserDto) {

        return RestResponse.status(Status.CREATED, userService.createUser(createUserDto));

    }

    @PUT
    @Path("/{userId}")
    public RestResponse<User> updateUser(@PathParam("userId") Long userId, @Valid UpdateUserDto updateUserDto) {

        return RestResponse.status(Status.OK, userService.updateUser(userId, updateUserDto));

    }

}
