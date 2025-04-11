package br.com.wareysis.repository.expense;

import br.com.wareysis.domain.expense.ExpenseEntry;
import br.com.wareysis.domain.expense.ExpenseEntryId;

import jakarta.enterprise.context.ApplicationScoped;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

@ApplicationScoped
public class ExpenseEntryRepository implements PanacheRepositoryBase<ExpenseEntry, ExpenseEntryId> {

}
