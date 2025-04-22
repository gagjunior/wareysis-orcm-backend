package br.com.wareysis.service.type;

import java.util.List;
import java.util.Objects;

import br.com.wareysis.core.service.AbstractService;
import br.com.wareysis.domain.type.AbstractType;
import br.com.wareysis.exception.type.TypeException;
import br.com.wareysis.repository.type.AbstractTypeRepository;

import jakarta.ws.rs.core.Response;

public abstract class AbstractTypeService<T extends AbstractType> extends AbstractService {

    protected AbstractTypeRepository<T> repository;

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
