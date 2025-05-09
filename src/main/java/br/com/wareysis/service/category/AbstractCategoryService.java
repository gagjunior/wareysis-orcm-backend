package br.com.wareysis.service.category;

import java.util.List;
import java.util.Objects;

import br.com.wareysis.core.service.AbstractService;
import br.com.wareysis.core.service.user.UserService;
import br.com.wareysis.domain.category.AbstractCategory;
import br.com.wareysis.domain.category.CategoryId;
import br.com.wareysis.domain.user.User;
import br.com.wareysis.dto.category.CategoryDto;
import br.com.wareysis.exception.category.CategoryException;
import br.com.wareysis.mapper.GenericCategoryMapper;
import br.com.wareysis.repository.AbstractCategoryRepository;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

public abstract class AbstractCategoryService<T extends AbstractCategory> extends AbstractService {

    @Inject
    UserService userService;

    protected AbstractCategoryRepository<T> repository;

    protected GenericCategoryMapper<T> mapper;

    @Transactional
    public CategoryDto create(CategoryDto dto, ContainerRequestContext ctx) {

        User user = userService.findUserByContext(ctx);

        validateCreateCategory(new CategoryId(user.getId(), dto.name()));

        T category = mapper.toEntity(dto);
        repository.persist(category);

        return mapper.toDto(category);
    }

    @Transactional
    public CategoryDto update(CategoryDto dto, ContainerRequestContext ctx) {

        User user = userService.findUserByContext(ctx);

        CategoryId categoryId = new CategoryId(user.getId(), dto.name());

        validateCategory(categoryId);

        T oldCategory = repository.findById(categoryId);

        if (Objects.nonNull(oldCategory)) {
            oldCategory.setDescription(dto.description());
        }

        repository.flush();

        return mapper.toDto(oldCategory);

    }

    @Transactional
    public void delete(String name, ContainerRequestContext ctx) {

        User user = userService.findUserByContext(ctx);

        CategoryId categoryId = new CategoryId(user.getId(), name);

        validateCategory(categoryId);

        T category = repository.findById(categoryId);

        if (Objects.nonNull(category)) {
            repository.delete(category);

        }

    }

    public List<CategoryDto> findAllByUserId(ContainerRequestContext ctx) {

        User user = userService.findUserByContext(ctx);

        userService.validateUser(user.getId());

        return repository.findAllByUserId(user.getId()).parallelStream().map(mapper::toDto).toList();
    }

    public List<CategoryDto> findAllByName(String name, ContainerRequestContext ctx) {

        User user = userService.findUserByContext(ctx);

        CategoryId categoryId = new CategoryId(user.getId(), name);

        userService.validateUser(categoryId.getUserId());

        return repository.findAllByName(categoryId).parallelStream().map(mapper::toDto).toList();
    }

    public void validateCategory(CategoryId categoryId) {

        userService.validateUser(categoryId.getUserId());

        if (!isCategoryPresent(categoryId)) {
            throw new CategoryException(messageService.getMessage("category.notFound"), Response.Status.NOT_FOUND);
        }
    }

    private void validateCreateCategory(CategoryId categoryId) {

        userService.validateUser(categoryId.getUserId());

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

}
