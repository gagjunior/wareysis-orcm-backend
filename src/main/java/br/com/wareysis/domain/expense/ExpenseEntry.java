package br.com.wareysis.domain.expense;

import java.io.Serializable;
import java.math.BigDecimal;

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
@Table(name = "expense_entry")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExpenseEntry extends AbstractDomainBase implements Serializable {

    @EmbeddedId
    ExpenseEntryId id;

    @Column(name = "category_name")
    private String categoryName;

    @Column(name = "supplier_name")
    private String supplierName;

    @Column(name = "entry_type_id")
    private Long entryTypeId;

    @Column(name = "status_type_id")
    private Long statusTypeId;

    @Column(name = "payment_type_id")
    private Long paymentTypeId;

    @Column(name = "payment_method_id")
    private Long paymentMethodId;

    @Column(name = "total_value")
    private BigDecimal totalValue;

    private String description;

}
