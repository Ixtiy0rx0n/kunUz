package org.example.kunuz.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Setter
@Getter
@Table(name = "article")
public class ArticleEntity{
    @Id
    private String id = UUID.randomUUID().toString();
    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false, columnDefinition = "text")
    private String content;

    @Column(nullable = false, name = "shared_count")
    private Integer sharedCount = 0;

    @Column(name = "visible")
    private Boolean visible = true;

    @ManyToOne
    @JoinColumn(name = "region_id")
    private RegionEntity region;

    @ManyToOne
    @JoinColumn(name = "moderator_id", nullable = false)
    private ProfileEntity moderator;

    @ManyToOne
    @JoinColumn(name = "publisher_id", nullable = false)
    private ProfileEntity publisher;

}
