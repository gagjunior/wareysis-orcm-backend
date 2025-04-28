package br.com.wareysis.dto.expense;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record ExpenseInstallmentDetailsDto(

        UUID uuid,

        @NotNull(message = "Due date cannot be null")
        LocalDate dueDate,

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
