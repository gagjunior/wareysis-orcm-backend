package br.com.wareysis.domain.income;

import br.com.wareysis.domain.category.AbstractCategory;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "income_category")
public class IncomeCategory extends AbstractCategory {

}
