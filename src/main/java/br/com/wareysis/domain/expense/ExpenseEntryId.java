package br.com.wareysis.domain.expense;

import java.io.Serializable;
import java.time.LocalDate;

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
public class ExpenseEntryId implements Serializable {

    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "entry_date")
    private LocalDate entryDate;

}
