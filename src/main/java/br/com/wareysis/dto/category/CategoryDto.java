package br.com.wareysis.dto.category;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CategoryDto(

        @NotNull(message = "User ID cannot be null")
        @Min(value = 2, message = "User ID must be greater than 1")
        Long userId,
        @NotBlank(message = "Name cannot be blank")
        String name,
        String description
) {

}
