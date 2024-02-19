package org.example.kunuz.repository;

import org.example.kunuz.entity.ArticleEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface ArticleRepository extends CrudRepository<ArticleEntity, String> {
    @Transactional
    @Modifying
    @Query("update ArticleEntity set visible=false where id=:id")
    public Integer delete(@Param("id") String id);

    @Transactional
    @Modifying
    @Query("from ArticleEntity where id=?1")
    Integer getArticleEntityById(String id);
    @Query("FROM ArticleEntity WHERE id=?1 and visible=true")
    Optional<ArticleEntity> findArticleEntity(String id);

    @Query("FROM ArticleEntity WHERE id=?1 AND visible=true ORDER BY createdDate desc LIMIT 5")
    ArticleEntity getById(String id);

    @Query("FROM ArticleEntity WHERE status='PUBLISHER' ORDER BY viewCount DESC LIMIT 4")
    List<ArticleEntity> getMostReadArticles();

    @Query("FROM ArticleEntity AS a WHERE a.regionId = :id AND a.status='PUBLISHER'")
    Page<ArticleEntity> getArticleListByRegionId(Pageable pageable, Integer id);
    @Query("FROM ArticleEntity AS a WHERE a.categoryId = :id AND a.status='PUBLISHER'")
    List<ArticleEntity> getLast5ArticleCategoryId(Integer id);
    @Query("FROM ArticleEntity AS a WHERE a.categoryId = :id AND a.status='PUBLISHER'")
    Page<ArticleEntity> getArticlesByCategoryKey(Pageable pageable,Integer id);
}
