package org.example.kunuz.repository;


import jakarta.transaction.Transactional;
import org.example.kunuz.entity.ArticleTypesEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface NewArticleTypeRepository extends CrudRepository<ArticleTypesEntity, Integer> {
    @Query("SELECT nat FROM ArticleTypesEntity AS nat WHERE nat.articleId=?1 AND nat.articleTypeId=?2 AND nat.visible=true ")
    Optional<ArticleTypesEntity> getArticleTypeId(String articleId, Integer articleTypeId);

    @Query("SELECT nat FROM ArticleTypesEntity AS nat WHERE nat.articleId=?1 AND nat.visible=true")
    Optional<ArticleTypesEntity> findArticleId(String articleId);

    @Transactional
    @Modifying
    @Query("DELETE FROM ArticleTypesEntity AS nat WHERE nat.visible=true AND nat.articleId=:articleId AND nat.articleTypeId=:articleTypeId")
    void deleteEn(@Param("articleId") String articleId, @Param("articleTypeId") Integer articleTypeId);

    @Transactional
    @Modifying
    @Query("update ArticleTypesEntity set updatedDate =?1  where articleId =?2 and articleTypeId=?3")
    void updateDate(LocalDateTime updatedDate, String articleId, Integer typeId);

    @Query("SELECT nat.articleId FROM ArticleTypesEntity AS nat WHERE nat.articleTypeId=?1 ORDER BY nat.createdDate limit 2")
    List<String> getArticleId(Integer id, Integer size);

    @Query("SELECT nat.articleId FROM ArticleTypesEntity AS nat WHERE nat.articleId <> ?1 AND nat.articleTypeId=?2 ORDER BY nat.createdDate limit 4")
    List<String> getArticleId(String aId, Integer articleTypeId);
}
