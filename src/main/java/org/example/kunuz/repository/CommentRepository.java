package org.example.kunuz.repository;

import org.example.kunuz.dto.CommentDTO;
import org.example.kunuz.entity.CommentEntity;
import org.example.kunuz.entity.RegionEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

public interface CommentRepository extends CrudRepository<CommentEntity, Integer> {

    @Transactional
    @Modifying
    @Query("update CommentEntity c set c.content=:content where c.id=:id")
    CommentDTO update(String content, Integer id);

    @Transactional
    @Modifying
    @Query("update CommentEntity c set c.visible=false where c.id=:id")
    Integer delete(Integer id);

    @Transactional
    @Modifying
    @Query("from CommentEntity where articleId=:id")
    CommentDTO getAllComment(String id);
    Page<CommentEntity> findAllByVisible(Pageable pageable, Boolean visible );

}
