package br.com.wareysis.repository.type;

import java.util.List;

import br.com.wareysis.domain.type.AbstractType;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import io.quarkus.panache.common.Parameters;

public abstract class AbstractTypeRepository<T extends AbstractType> implements PanacheRepositoryBase<T, Long> {

    public List<T> findAllByName(String name) {

        String likeParam = String.format("%%%s%%", name).toUpperCase();

        return find("UPPER(name) LIKE :name", Parameters.with("name", likeParam)).list();
    }

}
