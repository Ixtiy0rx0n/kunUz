package org.example.kunuz.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.example.kunuz.entity.BaseEntity;

@Getter
@Setter
@Entity
@Table(name = "article_type")
public class ArticleTypeEntity extends BaseEntity {
    private Integer orderNumber;
    private String nameUz;
    private String nameRu;
    private String nameEn;
    @Column(name = "article_id")
    private String articleId;
    @ManyToOne
    @JoinColumn(name = "article_id",insertable = false,updatable = false)
    private ArticleEntity article;

    @Column(name = "article_type_id")
    private Integer articleTypeId;
    @ManyToOne
    @JoinColumn(name = "article_type_id",insertable = false,updatable = false)
    private ArticleTypeEntity articleType;
}