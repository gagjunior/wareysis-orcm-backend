package br.com.wareysis.repository.expense;

import br.com.wareysis.domain.expense.ExpenseEntry;
import br.com.wareysis.domain.expense.ExpenseEntryId;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

@ApplicationScoped
public class ExpenseEntryRepository implements PanacheRepositoryBase<ExpenseEntry, ExpenseEntryId> {

    @Inject
    EntityManager entityManager;

    public Long nextValSequence() {

        return ((Number) entityManager.createNativeQuery("SELECT nextval('EXPENSE_ENTRY_ID_SEQ')").getSingleResult()).longValue();
    }

}
