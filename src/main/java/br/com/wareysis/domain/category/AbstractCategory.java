package br.com.wareysis.domain.category;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.MappedSuperclass;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@MappedSuperclass
public abstract class AbstractCategory extends PanacheEntityBase implements Serializable {

    @EmbeddedId
    private CategoryId id;

    @Column(nullable = false)
    private String description;

    @Column(name = "create_time")
    private LocalDateTime createTime;

    @Column(name = "update_time")
    private LocalDateTime updateTime;

}
