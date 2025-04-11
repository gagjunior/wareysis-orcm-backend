package br.com.wareysis.service.expense;

import br.com.wareysis.domain.expense.ExpenseEntry;
import br.com.wareysis.repository.expense.ExpenseEntryRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

@ApplicationScoped
public class ExpenseEntryService {

    @Inject
    ExpenseEntryRepository expenseEntryRepository;

    @Inject
    EntityManager em;

    public ExpenseEntry create(ExpenseEntry expenseEntry) {

        Long nextId = nextValSequence();
        expenseEntry.getId().setId(nextId);

        em.persist(expenseEntry);

        return expenseEntry;
    }

    private Long nextValSequence() {

        return ((Number) em.createNativeQuery("SELECT nextval('EXPENSE_ENTRY_ID_SEQ')").getSingleResult()).longValue();
    }

}
