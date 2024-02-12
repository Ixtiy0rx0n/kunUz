package org.example.kunuz.repository;

import org.example.kunuz.entity.ArticleEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

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
}
