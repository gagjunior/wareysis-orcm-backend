package br.com.wareysis.dto.expense;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record ExpenseInstallmentDto(

        UUID uuid,

        @NotNull(message = "Due date cannot be null")
        LocalDate dueDate,

        @NotNull(message = "Entry ID cannot be null")
        @Min(value = 1, message = "Entry ID must be greater than 0")
        Long entryId,

        @NotNull(message = "User ID cannot be null")
        @Min(value = 2, message = "User ID must be greater than 1")
        Integer userId,

        @NotNull(message = "Entry date cannot be null")
        LocalDate entryDate,

        @NotNull(message = "Amount cannot be null")
        @Min(value = 0, message = "Amount must be greater than or equal to zero")
        BigDecimal amount,

        @NotNull(message = "Status type cannot be null")
        @Min(value = 1, message = "Status type must be greater than 0")
        Integer statusType,

        BigDecimal amountPaid,
        LocalDateTime createTime,
        LocalDateTime updateTime
) {

}
