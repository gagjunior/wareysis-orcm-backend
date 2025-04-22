package br.com.wareysis.service.type;

import br.com.wareysis.domain.type.EntryType;
import br.com.wareysis.repository.type.EntryTypeRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class EntryTypeService extends AbstractTypeService<EntryType> {

    @Inject
    public EntryTypeService(EntryTypeRepository repository) {

        super.repository = repository;
    }

}
