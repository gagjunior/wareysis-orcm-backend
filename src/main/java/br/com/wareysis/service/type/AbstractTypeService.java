package br.com.wareysis.service.type;

import java.util.List;
import java.util.Objects;

import br.com.wareysis.core.service.AbstractService;
import br.com.wareysis.domain.type.AbstractType;
import br.com.wareysis.exception.type.TypeException;
import br.com.wareysis.repository.type.AbstractTypeRepository;

import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;

public class AbstractTypeService<T extends AbstractType> extends AbstractService {

    @Inject
    AbstractTypeRepository<T> repository;

    public List<T> findAllByName(String name) {

        if (Objects.isNull(name) || name.isBlank()) {
            throw new TypeException("Name cannot be null or empty", Response.Status.BAD_REQUEST);
        }

        return repository.findAllByName(name);
    }

    public List<T> findAll() {
        return repository.listAll();
    }

}
