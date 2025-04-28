package br.com.wareysis.dto.expense;

import java.time.LocalDate;
import java.util.List;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record ExpenseInstallmentDto(

        @NotNull(message = "Entry ID cannot be null")
        @Min(value = 1, message = "Entry ID must be greater than 0")
        Long entryId,

        @NotNull(message = "User ID cannot be null")
        @Min(value = 2, message = "User ID must be greater than 1")
        Long userId,

        @NotNull(message = "Entry date cannot be null")
        LocalDate entryDate,

        List<ExpenseInstallmentDetailsDto> installmentList

) {

}
