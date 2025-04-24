package br.com.wareysis.mapper.expense;

import java.math.BigDecimal;

import br.com.wareysis.domain.expense.ExpenseInstallment;
import br.com.wareysis.domain.expense.ExpenseInstallmentId;
import br.com.wareysis.dto.expense.ExpenseInstallmentDto;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ExpenseInstallmentMapper {

    public ExpenseInstallment toEntity(ExpenseInstallmentDto dto) {

        ExpenseInstallmentId expenseInstallmentId = new ExpenseInstallmentId(dto.uuid(), dto.dueDate());

        return ExpenseInstallment.builder()
                .id(expenseInstallmentId)
                .entryId(dto.entryId())
                .userId(dto.userId())
                .entryDate(dto.entryDate())
                .amount(dto.amount())
                .statusType(dto.statusType())
                .amountPaid(dto.amountPaid() == null ? BigDecimal.ZERO : dto.amountPaid())
                .build();
    }

    public ExpenseInstallmentDto toDto(ExpenseInstallment entity) {

        return new ExpenseInstallmentDto(
                entity.getId().getId(),
                entity.getId().getDueDate(),
                entity.getEntryId(),
                entity.getUserId(),
                entity.getEntryDate(),
                entity.getAmount(),
                entity.getStatusType(),
                entity.getAmountPaid(),
                entity.getCreateTime(),
                entity.getUpdateTime()
        );
    }

}
