package br.com.wareysis.controller.type;

import br.com.wareysis.domain.type.EntryType;
import br.com.wareysis.domain.type.PaymentMethodsType;
import br.com.wareysis.domain.type.PaymentType;
import br.com.wareysis.domain.type.StatusType;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

public class TypeController implements TypeApi {

    @GET
    @Path("/entry")
    public Response getEntryTypes() {

        return Response.ok().entity(EntryType.listAll()).build();
    }

    @GET
    @Path("/payment")
    public Response getPaymentTypes() {

        return Response.ok().entity(PaymentType.listAll()).build();
    }

    @GET
    @Path("/payment/methods")
    public Response getPaymentMethodsTypes() {

        return Response.ok().entity(PaymentMethodsType.listAll()).build();
    }

    @GET
    @Path("/status")
    public Response getStatusTypes() {

        return Response.ok().entity(StatusType.listAll()).build();
    }

}
