package br.com.wareysis.dto.expense;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record ExpenseEntryDto(

        Long id,

        @NotNull(message = "User ID cannot be null")
        @Min(value = 2, message = "User ID must be greater than 1")
        Long userId,

        @NotNull(message = "Entry date cannot be null")
        LocalDate entryDate,

        String categoryName,

        String supplierName,

        Long entryTypeId,

        Long statusTypeId,

        Long paymentTypeId,

        Long paymentMethodId,

        @NotNull(message = "Payment date cannot be null")
        LocalDate paymentDate,

        BigDecimal totalValue,

        String description,

        LocalDateTime createTime,

        LocalDateTime updateTime

) {

}
