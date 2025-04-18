package br.com.wareysis.service.category;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import br.com.wareysis.core.service.AbstractService;
import br.com.wareysis.domain.category.AbstractCategory;
import br.com.wareysis.domain.category.CategoryId;
import br.com.wareysis.dto.category.CategoryDto;
import br.com.wareysis.exception.category.CategoryException;
import br.com.wareysis.mapper.GenericCategoryMapper;
import br.com.wareysis.repository.AbstractCategoryRepository;
import br.com.wareysis.service.user.UserService;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

public abstract class AbstractCategoryService<T extends AbstractCategory> extends AbstractService {

    @Inject
    AbstractCategoryRepository<T> repository;

    @Inject
    GenericCategoryMapper<T> mapper;

    @Inject
    UserService userService;

    @Transactional
    public CategoryDto create(CategoryDto dto) {

        validateCreateCategory(new CategoryId(dto.userId(), dto.name()));

        T category = mapper.toEntity(dto);

        repository.persist(category);
        return dto;
    }

    @Transactional
    public void delete(CategoryId categoryId) {

        validateCategory(categoryId);

        T category = repository.findById(categoryId);

        repository.delete(category);
    }

    @Transactional
    public void update(CategoryDto dto) {

        CategoryId categoryId = new CategoryId(dto.userId(), dto.name());

        validateCategory(categoryId);

        T oldCategory = repository.findById(categoryId);
        oldCategory.setDescription(dto.description());
        oldCategory.setUpdateTime(LocalDateTime.now());

    }

    public List<CategoryDto> findAllByUserId(Long userId) {

        userService.validateUserExists(userId);

        return repository.findAllByUserId(userId).parallelStream().map(mapper::toDto).toList();
    }

    public List<CategoryDto> findAllByName(CategoryId categoryId) {

        userService.validateUserExists(categoryId.getUserId());

        return repository.findAllByName(categoryId).parallelStream().map(mapper::toDto).toList();
    }

    private void validateCreateCategory(CategoryId categoryId) {

        userService.validateUserExists(categoryId.getUserId());

        if (isCategoryPresent(categoryId)) {
            throw new CategoryException(messageService.getMessage("category.already.exists", categoryId.getName()), Status.BAD_REQUEST);
        }

        int qtdCategories = repository.countCategoriesByUserId(categoryId.getUserId()).intValue();

        if (qtdCategories >= 5) {
            throw new CategoryException(messageService.getMessage("category.limit.exceeded"), Response.Status.PRECONDITION_FAILED);
        }

    }

    private boolean isCategoryPresent(CategoryId categoryId) {

        List<T> categoryList = repository.findAllByName(categoryId);

        return Objects.nonNull(categoryList) && !categoryList.isEmpty();
    }

    public void validateCategory(CategoryId categoryId) {

        userService.validateUserExists(categoryId.getUserId());

        if (!isCategoryPresent(categoryId)) {
            throw new CategoryException(messageService.getMessage("category.notFound"), Response.Status.NOT_FOUND);
        }
    }

}
