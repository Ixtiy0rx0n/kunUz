package org.example.kunuz.repository;

import org.example.kunuz.entity.ArticleLikeEntity;
import org.example.kunuz.enums.ArticleLikeStatus;
import org.example.kunuz.enums.ProfileRole;
import org.example.kunuz.enums.ProfileStatus;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

public interface ArticleLikeRepository extends CrudRepository<ArticleLikeEntity, Integer> {

    @Transactional
    @Modifying
    @Query("update ArticleLikeEntity a set a.visible=:visible, a.status = NULL where a.id=:id")
    Integer update(Boolean visible, Integer id);

}
