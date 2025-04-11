package br.com.wareysis.controller.type;

import br.com.wareysis.service.type.EntryTypeService;
import br.com.wareysis.service.type.PaymentMethodsTypeService;
import br.com.wareysis.service.type.PaymentTypeService;
import br.com.wareysis.service.type.StatusTypeService;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

public class TypeController implements TypeApi {

    @Inject
    EntryTypeService entryTypeService;

    @Inject
    PaymentMethodsTypeService paymentMethodsTypeService;

    @Inject
    PaymentTypeService paymentTypeService;

    @Inject
    StatusTypeService statusTypeService;

    @GET
    @Path("/entry")
    public Response getEntryTypes() {

        return Response.ok().entity(entryTypeService.findAll()).build();
    }

    @GET
    @Path("/payment")
    public Response getPaymentTypes() {

        return Response.ok().entity(paymentTypeService.findAll()).build();
    }

    @GET
    @Path("/payment/methods")
    public Response getPaymentMethodsTypes() {

        return Response.ok().entity(paymentMethodsTypeService.findAll()).build();
    }

    @GET
    @Path("/status")
    public Response getStatusTypes() {

        return Response.ok().entity(statusTypeService.findAll()).build();
    }

}
