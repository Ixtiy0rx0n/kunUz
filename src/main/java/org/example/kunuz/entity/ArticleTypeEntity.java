package org.example.kunuz.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
@Entity
@Table(name = "article_type")
public class ArticleTypeEntity extends BaseEntity {
    @Column(nullable = false)
    private String nameUz;
    @Column(nullable = false)
    private String nameRu;
    @Column(nullable = false)
    private String nameEn;
    @Column(nullable = false)
    private Integer orderNumber;

}
