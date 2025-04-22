package br.com.wareysis.service.type;

import br.com.wareysis.domain.type.PaymentMethodsType;
import br.com.wareysis.repository.type.PaymentMethodsTypeRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class PaymentMethodsTypeService extends AbstractTypeService<PaymentMethodsType> {

    @Inject
    public PaymentMethodsTypeService(PaymentMethodsTypeRepository repository) {

        super.repository = repository;
    }

}
