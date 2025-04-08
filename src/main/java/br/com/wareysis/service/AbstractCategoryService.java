package br.com.wareysis.service;

import java.util.List;
import java.util.Objects;

import br.com.wareysis.core.messages.MessageService;
import br.com.wareysis.core.utils.DateTimeUtils;
import br.com.wareysis.domain.category.AbstractCategory;
import br.com.wareysis.domain.category.CategoryId;
import br.com.wareysis.dto.CategoryRequestDto;
import br.com.wareysis.exception.CategoryException;
import br.com.wareysis.mapper.GenericCategoryMapper;
import br.com.wareysis.repository.AbstractCategoryRepository;

import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;

public abstract class AbstractCategoryService<T extends AbstractCategory> {

    @Inject
    AbstractCategoryRepository<T> repository;

    @Inject
    GenericCategoryMapper<T> mapper;

    @Inject
    MessageService messageService;

    public CategoryRequestDto create(CategoryRequestDto dto) {

        validateCreateCategory(new CategoryId(dto.userId(), dto.name()));

        T category = mapper.toEntity(dto);

        category.setCreateTime(DateTimeUtils.dhUpdate());
        category.setUpdateTime(DateTimeUtils.dhUpdate());

        repository.persist(category);
        return dto;
    }

    public void delete(CategoryId categoryId) {

        validateCategory(categoryId);

        T category = repository.findById(categoryId);

        repository.delete(category);
    }

    public void update(CategoryRequestDto dto) {

        CategoryId categoryId = new CategoryId(dto.userId(), dto.name());

        validateCategory(categoryId);

        T oldCategory = repository.findById(categoryId);
        oldCategory.setDescription(dto.description());
        oldCategory.setUpdateTime(DateTimeUtils.dhUpdate());

    }

    public List<CategoryRequestDto> findAllByUserId(Long userId) {

        return repository.findAllByUserId(userId).parallelStream().map(mapper::toDto).toList();
    }

    public List<CategoryRequestDto> findAllByName(CategoryId categoryId) {

        return repository.findAllByName(categoryId).parallelStream().map(mapper::toDto).toList();
    }

    private void validateCreateCategory(CategoryId categoryId) {

        int qtdCategories = repository.countCategoriesByUserId(categoryId.getUserId()).intValue();

        if (qtdCategories >= 5) {
            throw new CategoryException(messageService.getMessage("category.limit.exceeded"), Response.Status.PRECONDITION_FAILED);
        }

    }

    private void validateCategory(CategoryId categoryId) {

        T category = repository.findById(categoryId);
        if (Objects.isNull(category)) {
            throw new CategoryException(messageService.getMessage("category.notFound"), Response.Status.NOT_FOUND);
        }
    }

}
