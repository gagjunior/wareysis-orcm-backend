package br.com.wareysis.controller.type;

import java.util.List;

import org.jboss.resteasy.reactive.RestResponse;
import org.jboss.resteasy.reactive.RestResponse.Status;

import br.com.wareysis.domain.type.EntryType;
import br.com.wareysis.domain.type.PaymentMethodsType;
import br.com.wareysis.domain.type.PaymentType;
import br.com.wareysis.domain.type.StatusType;
import br.com.wareysis.service.type.EntryTypeService;
import br.com.wareysis.service.type.PaymentMethodsTypeService;
import br.com.wareysis.service.type.PaymentTypeService;
import br.com.wareysis.service.type.StatusTypeService;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/v1/types")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TypeController {

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
    public RestResponse<List<EntryType>> getEntryTypes() {

        return RestResponse.status(Status.OK, entryTypeService.findAll());
    }

    @GET
    @Path("/payment")
    public RestResponse<List<PaymentType>> getPaymentTypes() {

        return RestResponse.status(Status.OK, paymentTypeService.findAll());
    }

    @GET
    @Path("/payment/methods")
    public RestResponse<List<PaymentMethodsType>> getPaymentMethodsTypes() {

        return RestResponse.status(Status.OK, paymentMethodsTypeService.findAll());
    }

    @GET
    @Path("/status")
    public RestResponse<List<StatusType>> getStatusTypes() {

        return RestResponse.status(Status.OK, statusTypeService.findAll());
    }

}
