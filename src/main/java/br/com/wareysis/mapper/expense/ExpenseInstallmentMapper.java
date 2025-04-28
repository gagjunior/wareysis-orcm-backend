package br.com.wareysis.mapper.expense;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import br.com.wareysis.domain.expense.ExpenseInstallment;
import br.com.wareysis.domain.expense.ExpenseInstallmentId;
import br.com.wareysis.dto.expense.ExpenseInstallmentDetailsDto;
import br.com.wareysis.dto.expense.ExpenseInstallmentDto;
import br.com.wareysis.exception.expense.ExpenseException;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.core.Response.Status;

@ApplicationScoped
public class ExpenseInstallmentMapper {

    public List<ExpenseInstallment> toEntity(ExpenseInstallmentDto dto) {

        if (dto.installmentList() == null || dto.installmentList().isEmpty()) {
            throw new ExpenseException("Lista de parcelas vazia ou null", Status.BAD_REQUEST);
        }

        return dto.installmentList()
                .stream()
                .map(installmentDetailsDto -> getExpenseInstallment(dto, installmentDetailsDto))
                .toList();

    }

    public ExpenseInstallmentDto toDto(List<ExpenseInstallment> entityList) {

        if (entityList == null || entityList.isEmpty()) {
            throw new ExpenseException("Lista de parcelas vazia ou null", Status.BAD_REQUEST);
        }

        Map<InstallmentKeyMap, List<ExpenseInstallment>> groupedInstallment = entityList.stream()
                .collect(Collectors.groupingBy(p -> new InstallmentKeyMap(
                                p.getEntryId(),
                                p.getUserId(),
                                p.getEntryDate()
                        )
                ));

        if (groupedInstallment.size() > 1) {
            throw new ExpenseException("Lista contém múltiplos grupos de parcelas", Status.BAD_REQUEST);
        }

        Map.Entry<InstallmentKeyMap, List<ExpenseInstallment>> entry = groupedInstallment.entrySet().iterator().next();

        return new ExpenseInstallmentDto(
                entry.getKey().getEntryId(),
                entry.getKey().getUserId(),
                entry.getKey().getEntryDate(),
                entry.getValue().stream()
                        .map(ExpenseInstallmentMapper::getInstallmentDetailsDto)
                        .toList()
        );

    }

    private static ExpenseInstallmentDetailsDto getInstallmentDetailsDto(ExpenseInstallment entity) {

        return new ExpenseInstallmentDetailsDto(
                entity.getId().getId(),
                entity.getId().getDueDate(),
                entity.getAmount(),
                entity.getStatusType(),
                entity.getAmountPaid(),
                entity.getCreateTime(),
                entity.getUpdateTime()
        );

    }

    private static ExpenseInstallment getExpenseInstallment(ExpenseInstallmentDto dto, ExpenseInstallmentDetailsDto installment) {

        ExpenseInstallment expenseInstallment = new ExpenseInstallment();

        expenseInstallment.setEntryId(dto.entryId());
        expenseInstallment.setUserId(dto.userId());
        expenseInstallment.setEntryDate(dto.entryDate());

        ExpenseInstallmentId expenseInstallmentId = new ExpenseInstallmentId(installment.uuid(), installment.dueDate());
        expenseInstallment.setId(expenseInstallmentId);
        expenseInstallment.setAmount(installment.amount());
        expenseInstallment.setStatusType(installment.statusType());
        expenseInstallment.setAmountPaid(installment.amountPaid());

        return expenseInstallment;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @EqualsAndHashCode
    private static class InstallmentKeyMap {

        private Long entryId;

        private Long userId;

        private LocalDate entryDate;
    }

}
