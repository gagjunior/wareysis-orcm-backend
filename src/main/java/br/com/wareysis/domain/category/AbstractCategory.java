package br.com.wareysis.domain.category;

import br.com.wareysis.core.domain.AbstractDomainBase;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.MappedSuperclass;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@MappedSuperclass
public abstract class AbstractCategory extends AbstractDomainBase {

    @EmbeddedId
    private CategoryId id;

    @Column(nullable = false)
    private String description;

}
