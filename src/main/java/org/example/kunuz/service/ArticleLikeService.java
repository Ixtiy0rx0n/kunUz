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

    /*public ArticleLikeDTO likeOrDislike(ArticleLikeDTO dto){
         Optional<ArticleLikeEntity> optional = articleLikeRepository.findLike(dto.getProfileId(), dto.getArticleId());
        if (optional.isEmpty()) {
            ArticleLikeEntity entity = new ArticleLikeEntity();
            entity.setStatus(dto.getStatus());
            entity.setArticleId(dto.getArticleId());
            entity.setProfileId(dto.getProfileId());
            entity.setCreatedDate(LocalDateTime.now());
            entity.setVisible(true);
            articleLikeRepository.save(entity);
            dto.setId(entity.getId());
            dto.setCreatedDate(entity.getCreatedDate());
            return dto;
        }
        else{
            ArticleLikeEntity entity = optional.get();
            entity.setProfileId(dto.getProfileId());
            entity.setArticleId(dto.getArticleId());
            entity.setStatus(dto.getStatus());
            return dto;
        }
    }*/
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
