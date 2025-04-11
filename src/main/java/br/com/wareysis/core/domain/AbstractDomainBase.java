package br.com.wareysis.core.domain;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

@Getter
@Setter
@MappedSuperclass
public class AbstractDomainBase extends PanacheEntityBase {

    @Column(name = "create_time")
    private LocalDateTime createTime;

    @Column(name = "update_time")
    private LocalDateTime updateTime;

    @PrePersist
    public void onCreate() {

        this.createTime = LocalDateTime.now();
        this.updateTime = LocalDateTime.now();
    }

    @PreUpdate
    public void onUpdate() {

        this.updateTime = LocalDateTime.now();
    }

}
