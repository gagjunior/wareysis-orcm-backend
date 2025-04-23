package br.com.wareysis.repository.expense;

import java.util.ArrayList;
import java.util.List;

import br.com.wareysis.domain.expense.ExpenseEntry;
import br.com.wareysis.domain.expense.ExpenseEntryId;
import br.com.wareysis.dto.expense.ExpenseEntryFilterDto;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.Predicate;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

@ApplicationScoped
public class ExpenseEntryRepository implements PanacheRepositoryBase<ExpenseEntry, ExpenseEntryId> {

    @Inject
    EntityManager entityManager;

    public Long nextValSequence() {

        return ((Number) entityManager.createNativeQuery("SELECT nextval('EXPENSE_ENTRY_ID_SEQ')").getSingleResult()).longValue();
    }

    public List<ExpenseEntry> findAllByUserId(Long userId) {

        return list("id.userId", userId);
    }

    public List<ExpenseEntry> findByFilter(ExpenseEntryFilterDto filter) {

        var cb = entityManager.getCriteriaBuilder();
        var cq = cb.createQuery(ExpenseEntry.class);
        var root = cq.from(ExpenseEntry.class);

        List<Predicate> predicates = new ArrayList<>();

        if (filter.id() != null && filter.id() > 0) {
            predicates.add(cb.equal(root.get("id").get("id"), filter.id()));
        }

        if (filter.userId() != null && filter.userId() > 0) {
            predicates.add(cb.equal(root.get("id").get("userId"), filter.userId()));
        }

        if (filter.startEntryDate() != null && filter.endEntryDate() != null) {
            predicates.add(cb.between(root.get("id").get("entryDate"), filter.startEntryDate(), filter.endEntryDate()));
        } else if (filter.startEntryDate() != null) {
            predicates.add(cb.greaterThanOrEqualTo(root.get("id").get("entryDate"), filter.startEntryDate()));
        } else if (filter.endEntryDate() != null) {
            predicates.add(cb.lessThanOrEqualTo(root.get("id").get("entryDate"), filter.endEntryDate()));
        }

        if (filter.categoryName() != null && !filter.categoryName().isBlank()) {
            predicates.add(cb.like(cb.lower(root.get("categoryName")), "%" + filter.categoryName().toLowerCase() + "%"));
        }

        if (filter.supplierName() != null && !filter.supplierName().isBlank()) {
            predicates.add(cb.like(cb.lower(root.get("supplierName")), "%" + filter.supplierName().toLowerCase() + "%"));
        }

        if (filter.entryTypeId() != null && filter.entryTypeId() > 0) {
            predicates.add(cb.equal(root.get("entryTypeId"), filter.entryTypeId()));
        }

        if (filter.statusTypeId() != null && filter.statusTypeId() > 0) {
            predicates.add(cb.equal(root.get("statusTypeId"), filter.statusTypeId()));
        }

        if (filter.paymentTypeId() != null && filter.paymentTypeId() > 0) {
            predicates.add(cb.equal(root.get("paymentTypeId"), filter.paymentTypeId()));
        }

        if (filter.paymentMethodId() != null && filter.paymentMethodId() > 0) {
            predicates.add(cb.equal(root.get("paymentMethodId"), filter.paymentMethodId()));
        }

        if (filter.minTotalValue() != null) {
            predicates.add(cb.greaterThanOrEqualTo(root.get("totalValue"), filter.minTotalValue()));
        }

        if (filter.maxTotalValue() != null) {
            predicates.add(cb.lessThanOrEqualTo(root.get("totalValue"), filter.maxTotalValue()));
        }

        cq.where(predicates.toArray(Predicate[]::new));
        cq.orderBy(cb.desc(root.get("createTime")));

        return entityManager.createQuery(cq).getResultList();

    }

}
