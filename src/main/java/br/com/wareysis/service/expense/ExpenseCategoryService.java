package br.com.wareysis.service.expense;

import br.com.wareysis.domain.expense.ExpenseCategory;
import br.com.wareysis.mapper.expense.ExpenseCategoryMapper;
import br.com.wareysis.repository.expense.ExpenseCategoryRepository;
import br.com.wareysis.service.category.AbstractCategoryService;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class ExpenseCategoryService extends AbstractCategoryService<ExpenseCategory> {

    @Inject
    public ExpenseCategoryService(
            ExpenseCategoryRepository repository,
            ExpenseCategoryMapper mapper
    ) {

        super.repository = repository;
        super.mapper = mapper;
    }

}
