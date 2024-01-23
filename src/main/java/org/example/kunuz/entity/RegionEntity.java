package org.example.kunuz.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "region")
public class RegionEntity extends BaseEntity{
    @Column(nullable = false)
    private String nameUz;
    @Column(nullable = false)
    private String nameRu;
    @Column(nullable = false)
    private String nameEn;
    @Column(nullable = false)
    private Integer orderNumber;
}
