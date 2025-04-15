package br.com.wareysis.service.expense;

import br.com.wareysis.domain.category.CategoryId;
import br.com.wareysis.domain.expense.ExpenseEntry;
import br.com.wareysis.dto.expense.ExpenseEntryDto;
import br.com.wareysis.mapper.expense.ExpenseEntryMapper;
import br.com.wareysis.repository.expense.ExpenseEntryRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class ExpenseEntryService {

    @Inject
    ExpenseEntryRepository repository;

    @Inject
    EntityManager entityManager;

    @Inject
    ExpenseEntryMapper mapper;

    @Inject
    ExpenseCategoryService expenseCategoryService;

    @Transactional
    public ExpenseEntryDto create(ExpenseEntryDto dto) {

        expenseCategoryService.validateCategory(new CategoryId(dto.userId(), dto.categoryName()));

        Long nextId = nextValSequence();
        ExpenseEntry expenseEntry = mapper.toEntity(dto, nextId);

        entityManager.persist(expenseEntry);

        return mapper.toDto(expenseEntry);
    }

    private Long nextValSequence() {

        return ((Number) entityManager.createNativeQuery("SELECT nextval('EXPENSE_ENTRY_ID_SEQ')").getSingleResult()).longValue();
    }

}
