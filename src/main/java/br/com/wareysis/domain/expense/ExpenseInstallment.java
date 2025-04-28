package br.com.wareysis.domain.expense;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import br.com.wareysis.core.domain.AbstractDomainBase;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "EXPENSE_INSTALLMENT")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExpenseInstallment extends AbstractDomainBase implements Serializable {

    @EmbeddedId
    public ExpenseInstallmentId id;

    @Column(name = "ENTRY_ID", nullable = false)
    public Long entryId;

    @Column(name = "USER_ID", nullable = false)
    public Long userId;

    @Column(name = "ENTRY_DATE", nullable = false)
    public LocalDate entryDate;

    @Column(name = "AMOUNT", nullable = false, precision = 12, scale = 3)
    public BigDecimal amount;

    @Column(name = "STATUS_TYPE", nullable = false)
    public Integer statusType;

    @Builder.Default
    @Column(name = "AMOUNT_PAID", nullable = false)
    public BigDecimal amountPaid = BigDecimal.ZERO;

}
