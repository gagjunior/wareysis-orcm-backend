package br.com.wareysis.domain.expense;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Embeddable
public class ExpenseInstallmentId implements Serializable {

    @Column(columnDefinition = "uuid")
    private UUID id;

    @Column(name = "due_date", nullable = false)
    private LocalDate dueDate;

}
