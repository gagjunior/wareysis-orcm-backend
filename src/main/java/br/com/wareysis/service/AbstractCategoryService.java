package br.com.wareysis.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import br.com.wareysis.core.service.AbstractService;
import br.com.wareysis.domain.category.AbstractCategory;
import br.com.wareysis.domain.category.CategoryId;
import br.com.wareysis.dto.CategoryDto;
import br.com.wareysis.exception.category.CategoryException;
import br.com.wareysis.mapper.GenericCategoryMapper;
import br.com.wareysis.repository.AbstractCategoryRepository;

import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;

public abstract class AbstractCategoryService<T extends AbstractCategory> extends AbstractService {

    @Inject
    AbstractCategoryRepository<T> repository;

    @Inject
    GenericCategoryMapper<T> mapper;

    public CategoryDto create(CategoryDto dto) {

        validateCreateCategory(new CategoryId(dto.userId(), dto.name()));

        T category = mapper.toEntity(dto);

        category.setCreateTime(LocalDateTime.now());
        category.setUpdateTime(LocalDateTime.now());

        repository.persist(category);
        return dto;
    }

    public void delete(CategoryId categoryId) {

        validateCategory(categoryId);

        T category = repository.findById(categoryId);

        repository.delete(category);
    }

    public void update(CategoryDto dto) {

        CategoryId categoryId = new CategoryId(dto.userId(), dto.name());

        validateCategory(categoryId);

        T oldCategory = repository.findById(categoryId);
        oldCategory.setDescription(dto.description());
        oldCategory.setUpdateTime(LocalDateTime.now());

    }

    public List<CategoryDto> findAllByUserId(Long userId) {

        return repository.findAllByUserId(userId).parallelStream().map(mapper::toDto).toList();
    }

    public List<CategoryDto> findAllByName(CategoryId categoryId) {

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
