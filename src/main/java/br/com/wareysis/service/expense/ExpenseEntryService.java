package br.com.wareysis.service.expense;

import java.util.List;
import java.util.Objects;

import br.com.wareysis.core.service.AbstractService;
import br.com.wareysis.core.service.user.UserService;
import br.com.wareysis.domain.category.CategoryId;
import br.com.wareysis.domain.expense.ExpenseEntry;
import br.com.wareysis.domain.expense.ExpenseEntryId;
import br.com.wareysis.dto.expense.ExpenseEntryDto;
import br.com.wareysis.dto.expense.ExpenseEntryFilterDto;
import br.com.wareysis.exception.expense.ExpenseException;
import br.com.wareysis.mapper.expense.ExpenseEntryMapper;
import br.com.wareysis.repository.expense.ExpenseEntryRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response.Status;

@ApplicationScoped
public class ExpenseEntryService extends AbstractService {

    @Inject
    ExpenseEntryRepository repository;

    @Inject
    ExpenseEntryMapper mapper;

    @Inject
    ExpenseCategoryService categoryService;

    @Inject
    UserService userService;

    private static final String EXPENSE_ID_NOT_EXISTS = "expense.id.not.exists";

    @Transactional
    public ExpenseEntryDto create(ExpenseEntryDto dto) {

        categoryService.validateCategory(new CategoryId(dto.userId(), dto.categoryName()));

        Long nextId = repository.nextValSequence();
        ExpenseEntry expenseEntry = mapper.toEntity(dto, nextId);

        repository.persist(expenseEntry);

        return mapper.toDto(expenseEntry);
    }

    @Transactional
    public ExpenseEntryDto update(ExpenseEntryDto dto) {

        userService.validateUser(dto.userId());

        if (Objects.isNull(dto.id()) || dto.id() <= 0) {
            throw new ExpenseException(messageService.getMessage(EXPENSE_ID_NOT_EXISTS, dto.id()), Status.BAD_REQUEST);
        }

        ExpenseEntryId id = new ExpenseEntryId(dto.id(), dto.userId(), dto.entryDate());

        ExpenseEntry expenseEntry = validateExpenseEntryExists(id);

        mapper.updateFromDto(dto, expenseEntry);

        repository.flush();

        return mapper.toDto(expenseEntry);

    }

    public void delete(ExpenseEntryId id) {

        userService.validateUser(id.getUserId());

        if (Objects.isNull(id.getId()) || id.getId() <= 0) {
            throw new ExpenseException(messageService.getMessage(EXPENSE_ID_NOT_EXISTS, id.getId()), Status.BAD_REQUEST);
        }

        ExpenseEntry expenseEntry = validateExpenseEntryExists(id);

        repository.delete(expenseEntry);

    }

    public List<ExpenseEntryDto> findAllByUserId(Long userId) {

        return repository.findAllByUserId(userId).parallelStream().map(mapper::toDto).toList();
    }

    public List<ExpenseEntryDto> findByFilter(ExpenseEntryFilterDto filterDto) {

        return repository.findByFilter(filterDto).parallelStream().map(mapper::toDto).toList();
    }

    public ExpenseEntry validateExpenseEntryExists(ExpenseEntryId id) {

        return repository.findByIdOptional(id)
                .orElseThrow(() -> new ExpenseException(messageService.getMessage(EXPENSE_ID_NOT_EXISTS, id), Status.BAD_REQUEST));

    }

}
