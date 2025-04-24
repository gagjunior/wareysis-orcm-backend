package br.com.wareysis.repository.expense;

import br.com.wareysis.domain.expense.ExpenseInstallment;
import br.com.wareysis.domain.expense.ExpenseInstallmentId;

import jakarta.enterprise.context.ApplicationScoped;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

@ApplicationScoped
public class ExpenseInstallmentRepository implements PanacheRepositoryBase<ExpenseInstallment, ExpenseInstallmentId> {

}
