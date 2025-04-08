package br.com.wareysis.mapper.income;

import br.com.wareysis.core.utils.DateTimeUtils;
import br.com.wareysis.domain.category.CategoryId;
import br.com.wareysis.domain.income.IncomeCategory;
import br.com.wareysis.dto.CategoryRequestDto;
import br.com.wareysis.mapper.GenericCategoryMapper;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class IncomeCategoryMapper implements GenericCategoryMapper<IncomeCategory> {

    @Override
    public IncomeCategory toEntity(CategoryRequestDto dto) {

        IncomeCategory entity = new IncomeCategory();
        entity.setId(new CategoryId(dto.userId(), dto.name()));
        entity.setDescription(dto.description());
        entity.setUpdateTime(DateTimeUtils.dhUpdate());
        return entity;
    }

    @Override
    public CategoryRequestDto toDto(IncomeCategory entity) {

        CategoryId id = entity.getId();
        return new CategoryRequestDto(
                id.getUserId(),
                id.getName(),
                entity.getDescription()
        );
    }

}
