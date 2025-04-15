package br.com.wareysis.mapper.expense;

import java.time.LocalDateTime;

import br.com.wareysis.domain.category.CategoryId;
import br.com.wareysis.domain.expense.ExpenseCategory;
import br.com.wareysis.dto.category.CategoryDto;
import br.com.wareysis.mapper.GenericCategoryMapper;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ExpenseCategoryMapper implements GenericCategoryMapper<ExpenseCategory> {

    @Override
    public ExpenseCategory toEntity(CategoryDto dto) {

        ExpenseCategory entity = new ExpenseCategory();
        entity.setId(new CategoryId(dto.userId(), dto.name()));
        entity.setDescription(dto.description());
        entity.setUpdateTime(LocalDateTime.now());
        return entity;
    }

    @Override
    public CategoryDto toDto(ExpenseCategory entity) {

        CategoryId id = entity.getId();
        return new CategoryDto(
                id.getUserId(),
                id.getName(),
                entity.getDescription()
        );
    }

}
