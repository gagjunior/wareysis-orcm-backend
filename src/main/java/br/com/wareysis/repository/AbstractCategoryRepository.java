package br.com.wareysis.repository;

import java.util.List;

import br.com.wareysis.domain.category.AbstractCategory;
import br.com.wareysis.domain.category.CategoryId;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

public abstract class AbstractCategoryRepository<T extends AbstractCategory> implements PanacheRepositoryBase<T, CategoryId> {

    public List<T> findAllByUserId(Long userId) {

        return find("id.userId IN (1, ?1)", userId).list();
    }

    public Long countCategoriesByUserId(Long userId) {

        return count("id.userId", userId);
    }

    public List<T> findAllByName(CategoryId categoryId) {

        return find("id.userId IN (1, ?1) AND id.name LIKE ?2", categoryId.getUserId(), "%" + categoryId.getName() + "%").list();
    }

}
