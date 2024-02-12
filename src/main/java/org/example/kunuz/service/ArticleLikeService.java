package org.example.kunuz.service;

import org.example.kunuz.dto.ArticleLikeDTO;
import org.example.kunuz.entity.ArticleLikeEntity;
import org.example.kunuz.repository.ArticleLikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class ArticleLikeService{
    @Autowired
    private ArticleLikeRepository articleLikeRepository;

    public Boolean likeOrDislike(ArticleLikeDTO dto){
        ArticleLikeEntity entity = new ArticleLikeEntity();
        entity.setStatus(dto.getStatus());
        entity.setArticleId(dto.getArticleId());
        entity.setProfileId(dto.getProfileId());
        entity.setCreatedDate(LocalDateTime.now());
        entity.setVisible(true);
        articleLikeRepository.save(entity);
        return true;
    }
    public Boolean removeLike(ArticleLikeDTO dto){
        Optional<ArticleLikeEntity> optional = articleLikeRepository.findById(dto.getId());
        ArticleLikeEntity entity = optional.get();
        if (optional.isPresent()) {
            articleLikeRepository.update(false, entity.getId());
        }
        return true;

    }



    private ArticleLikeDTO toDTO(ArticleLikeDTO dto){
        return null;
    }
}
