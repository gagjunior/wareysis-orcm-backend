package br.com.wareysis.mapper.expense;

import br.com.wareysis.core.utils.DateTimeUtils;
import br.com.wareysis.domain.category.CategoryId;
import br.com.wareysis.domain.expense.ExpenseCategory;
import br.com.wareysis.dto.CategoryRequestDto;
import br.com.wareysis.mapper.GenericCategoryMapper;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ExpenseCategoryMapper implements GenericCategoryMapper<ExpenseCategory> {

    @Override
    public ExpenseCategory toEntity(CategoryRequestDto dto) {

        ExpenseCategory entity = new ExpenseCategory();
        entity.setId(new CategoryId(dto.userId(), dto.name()));
        entity.setDescription(dto.description());
        entity.setUpdateTime(DateTimeUtils.dhUpdate());
        return entity;
    }

    @Override
    public CategoryRequestDto toDto(ExpenseCategory entity) {

        CategoryId id = entity.getId();
        return new CategoryRequestDto(
                id.getUserId(),
                id.getName(),
                entity.getDescription()
        );
    }

}
