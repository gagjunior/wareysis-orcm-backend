package br.com.wareysis.mapper.income;

import java.time.LocalDateTime;

import br.com.wareysis.domain.category.CategoryId;
import br.com.wareysis.domain.income.IncomeCategory;
import br.com.wareysis.dto.category.CategoryDto;
import br.com.wareysis.mapper.GenericCategoryMapper;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class IncomeCategoryMapper implements GenericCategoryMapper<IncomeCategory> {

    @Override
    public IncomeCategory toEntity(CategoryDto dto) {

        IncomeCategory entity = new IncomeCategory();
        entity.setId(new CategoryId(dto.userId(), dto.name()));
        entity.setDescription(dto.description());
        entity.setUpdateTime(LocalDateTime.now());
        return entity;
    }

    @Override
    public CategoryDto toDto(IncomeCategory entity) {

        CategoryId id = entity.getId();
        return new CategoryDto(
                id.getUserId(),
                id.getName(),
                entity.getDescription()
        );
    }

}
