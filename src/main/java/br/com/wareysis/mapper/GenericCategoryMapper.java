package br.com.wareysis.mapper;

import br.com.wareysis.domain.category.AbstractCategory;
import br.com.wareysis.dto.CategoryDto;

public interface GenericCategoryMapper<T extends AbstractCategory> {

    T toEntity(CategoryDto dto);

    CategoryDto toDto(T entity);
}
