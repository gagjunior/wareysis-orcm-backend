package br.com.wareysis.service.type;

import br.com.wareysis.domain.type.PaymentType;
import br.com.wareysis.repository.type.PaymentTypeRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class PaymentTypeService extends AbstractTypeService<PaymentType> {

    @Inject
    public PaymentTypeService(PaymentTypeRepository repository) {

        super.repository = repository;
    }

}
