package br.com.wareysis.service.type;

import br.com.wareysis.domain.type.StatusType;
import br.com.wareysis.repository.type.StatusTypeRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class StatusTypeService extends AbstractTypeService<StatusType> {

    @Inject
    public StatusTypeService(StatusTypeRepository repository) {

        super.repository = repository;
    }

}
