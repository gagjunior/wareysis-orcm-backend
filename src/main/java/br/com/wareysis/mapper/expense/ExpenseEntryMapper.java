package br.com.wareysis.mapper.expense;

import java.math.BigDecimal;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;

import br.com.wareysis.domain.expense.ExpenseEntry;
import br.com.wareysis.domain.expense.ExpenseEntryId;
import br.com.wareysis.dto.expense.ExpenseEntryDto;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ExpenseEntryMapper {

    public ExpenseEntry toEntity(ExpenseEntryDto dto, Long id) {

        ExpenseEntryId expenseEntryId = new ExpenseEntryId(id, dto.userId(), dto.entryDate());

        return ExpenseEntry.builder()
                .id(expenseEntryId)
                .categoryName(dto.categoryName())
                .supplierName(dto.supplierName())
                .entryTypeId(dto.entryTypeId())
                .statusTypeId(dto.statusTypeId())
                .paymentTypeId(dto.paymentTypeId())
                .paymentMethodId(dto.paymentMethodId())
                .paymentDate(dto.paymentDate())
                .totalValue(dto.totalValue())
                .description(dto.description())
                .build();
    }

    public ExpenseEntryDto toDto(ExpenseEntry entity) {

        return new ExpenseEntryDto(
                entity.getId().getId(),
                entity.getId().getUserId(),
                entity.getId().getEntryDate(),
                entity.getCategoryName(),
                entity.getSupplierName(),
                entity.getEntryTypeId(),
                entity.getStatusTypeId(),
                entity.getPaymentTypeId(),
                entity.getPaymentMethodId(),
                entity.getPaymentDate(),
                entity.getTotalValue(),
                entity.getDescription(),
                entity.getCreateTime(),
                entity.getUpdateTime()
        );
    }

    public void updateFromDto(ExpenseEntryDto dto, ExpenseEntry entity) {

        if (StringUtils.isNotBlank(dto.categoryName())) {
            entity.setCategoryName(dto.categoryName());
        }

        if (StringUtils.isNotBlank(dto.supplierName())) {
            entity.setSupplierName(dto.supplierName());
        }

        if (isValidNumberField(dto.entryTypeId())) {
            entity.setEntryTypeId(dto.entryTypeId());
        }

        if (isValidNumberField(dto.statusTypeId())) {
            entity.setStatusTypeId(dto.statusTypeId());
        }

        if (isValidNumberField(dto.paymentTypeId())) {
            entity.setPaymentTypeId(dto.paymentTypeId());
        }

        if (isValidNumberField(dto.paymentMethodId())) {
            entity.setPaymentMethodId(dto.paymentMethodId());
        }

        if (isValidNumberField(dto.totalValue())) {
            entity.setTotalValue(dto.totalValue());
        }

        if (StringUtils.isNotBlank(dto.description())) {
            entity.setDescription(dto.description());
        }

    }

    private boolean isValidNumberField(Number value) {

        if (Objects.isNull(value)) {
            return false;
        }

        return switch (value) {
            case Long l when l > 0 -> true;
            case BigDecimal bd when bd.compareTo(BigDecimal.ZERO) > 0 -> true;
            default -> false;
        };

    }

}
