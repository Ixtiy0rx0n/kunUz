package org.example.kunuz.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "comment")
public class CommentEntity extends BaseEntity {
    @Column(name = "profile_id")
    private Integer profileId;
    @Column(name = "article_id")
    private String articleId;
    @Column(columnDefinition = "text")
    private String content;
    @ManyToOne
    @JoinColumn(name = "profile_id", insertable = false, updatable = false)
    private ProfileEntity profile;
    @ManyToOne
    @JoinColumn(name = "article_id", insertable = false, updatable = false)
    private ArticleEntity article;
}
