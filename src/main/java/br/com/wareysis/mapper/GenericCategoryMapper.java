package br.com.wareysis.mapper;

import br.com.wareysis.domain.category.AbstractCategory;
import br.com.wareysis.dto.CategoryRequestDto;

public interface GenericCategoryMapper<T extends AbstractCategory> {

    T toEntity(CategoryRequestDto dto);

    CategoryRequestDto toDto(T entity);
}
