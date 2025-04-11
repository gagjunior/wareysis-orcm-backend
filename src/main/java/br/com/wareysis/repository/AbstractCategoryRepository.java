package br.com.wareysis.repository;

import java.util.List;

import br.com.wareysis.domain.category.AbstractCategory;
import br.com.wareysis.domain.category.CategoryId;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import io.quarkus.panache.common.Parameters;

public abstract class AbstractCategoryRepository<T extends AbstractCategory> implements PanacheRepositoryBase<T, CategoryId> {

    public List<T> findAllByUserId(Long userId) {

        return find("id.userId IN (1, :userId)",
                Parameters.with("userId", userId)).list();
    }

    public Long countCategoriesByUserId(Long userId) {

        return count("id.userId", userId);
    }

    public List<T> findAllByName(CategoryId categoryId) {

        return find("id.userId IN (1, :userId) AND id.name LIKE :name",
                Parameters.with("userId", categoryId.getUserId())
                        .and("name", "%" + categoryId.getName() + "%")).list();
    }

}
