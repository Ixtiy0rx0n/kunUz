package org.example.kunuz.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.example.kunuz.enums.ArticleStatus;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Entity
@Setter
@Getter
@Table(name = "article")
public class ArticleEntity {
    @Id
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false, columnDefinition = "text")
    private String content;
    @Column(nullable = false, name = "shared_count")
    private Integer sharedCount = 0;

    @Column(name = "region_id")
    private Integer regionId;
    @ManyToOne
    @JoinColumn(name = "region_id", insertable = false, updatable = false)
    private RegionEntity region;

    @Column(name = "moderator_id")
    private Integer moderatorId;
    @ManyToOne
    @JoinColumn(name = "moderator_id", nullable = false,insertable = false, updatable = false)
    private ProfileEntity moderator;

    @Column(name = "publisher_id")
    private Integer publisherId;
    @ManyToOne
    @JoinColumn(name = "publisher_id",insertable = false, updatable = false)
    private ProfileEntity publisher;

    @Column(name = "category_id")
    private Integer categoryId;
    @ManyToOne
    @JoinColumn(name = "category_id",insertable = false, updatable = false)
    private CategoryEntity category;

    @Column(name = "photo_id")
    private String photoId;
    @ManyToOne
    @JoinColumn(name = "photo_id", nullable = false,insertable = false, updatable = false)
    private AttachEntity photo;

    @Column(name = "status")
    private ArticleStatus status;

    @Column(name = "visible")
    private boolean visible = true;
 /*   @ManyToOne
    @JoinColumn(name = "category_id")
    private CategoryEntity category;
    @Column(nullable = false)
    private ArticleStatus status;
    @Column(name = "created_date")
    private LocalDateTime createdDate = LocalDateTime.now();
    @Column(name = "published_date", nullable = false)
    private LocalDateTime publishedDate;

    @OneToMany(fetch = FetchType.LAZY)
    private List<ArticleTypeEntity> typeList;*/
}
