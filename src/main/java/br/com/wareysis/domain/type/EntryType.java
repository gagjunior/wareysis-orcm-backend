package br.com.wareysis.domain.type;

import org.hibernate.annotations.Immutable;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Immutable
@Table(name = "entry_type")
public class EntryType extends AbstractType {

}
