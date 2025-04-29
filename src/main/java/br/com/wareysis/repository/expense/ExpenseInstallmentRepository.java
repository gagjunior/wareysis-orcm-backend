package br.com.wareysis.repository.expense;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import br.com.wareysis.domain.expense.ExpenseEntryId;
import br.com.wareysis.domain.expense.ExpenseInstallment;
import br.com.wareysis.domain.expense.ExpenseInstallmentId;

import jakarta.enterprise.context.ApplicationScoped;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import io.quarkus.panache.common.Parameters;

@ApplicationScoped
public class ExpenseInstallmentRepository implements PanacheRepositoryBase<ExpenseInstallment, ExpenseInstallmentId> {

    public List<ExpenseInstallment> findAllByExpenseEntryId(ExpenseEntryId expenseEntryId) {

        return list("entryId = :entryId AND userId = :userId AND entryDate = :entryDate ORDER BY id.dueDate", Map.of(
                "entryId", expenseEntryId.getId(),
                "userId", expenseEntryId.getUserId(),
                "entryDate", expenseEntryId.getEntryDate()
        ));
    }

    public Optional<ExpenseInstallment> findByUUID(UUID uuid) {

        return find("id.id = :uuid_param", Parameters.with("uuid_param", uuid)).singleResultOptional();
    }

}
