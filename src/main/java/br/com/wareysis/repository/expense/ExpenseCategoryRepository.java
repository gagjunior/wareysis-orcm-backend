package br.com.wareysis.repository.expense;

import br.com.wareysis.domain.expense.ExpenseCategory;
import br.com.wareysis.repository.AbstractCategoryRepository;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ExpenseCategoryRepository extends AbstractCategoryRepository<ExpenseCategory> {

}
