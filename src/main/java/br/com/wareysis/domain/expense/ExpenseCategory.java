package br.com.wareysis.domain.expense;

import java.io.Serializable;

import br.com.wareysis.domain.category.AbstractCategory;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "expense_category")
public class ExpenseCategory extends AbstractCategory implements Serializable {

}
