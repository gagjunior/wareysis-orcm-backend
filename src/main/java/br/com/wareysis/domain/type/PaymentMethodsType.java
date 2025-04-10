package br.com.wareysis.domain.type;

import org.hibernate.annotations.Immutable;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Immutable
@Table(name = "payment_methods")
public class PaymentMethodsType extends AbstractType {

}
