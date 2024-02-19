package com.example.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
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
}