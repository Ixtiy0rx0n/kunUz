package org.example.kunuz.repository;

import org.example.kunuz.entity.ArticleLikeEntity;
import org.example.kunuz.enums.ArticleLikeStatus;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface ArticleLikeRepository extends CrudRepository<ArticleLikeEntity, Integer> {

    @Transactional
    @Modifying
    @Query("update ArticleLikeEntity a set a.visible=:visible, a.status = NULL where a.id=:id")
    Integer update(Boolean visible, Integer id);

    @Transactional
    @Modifying
    @Query("update ArticleLikeEntity a set a.status=:status where a.id=:id")
    Integer updateLike(ArticleLikeStatus status, Integer id);

//    @Transactional
//    @Modifying
//    @Query("from ArticleLikeEntity where profileId=:profileId and articleId=:aricleId")
//    Integer findLike(Integer profileId, String articleId);

}
