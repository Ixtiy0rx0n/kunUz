package org.example.kunuz.service;

import org.example.kunuz.dto.ArticleCreateDTO;
import org.example.kunuz.entity.ArticleEntity;
import org.example.kunuz.enums.ArticleStatus;
import org.example.kunuz.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ArticleService {
    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private ArticleTypesService articleTypesService;

    public ArticleCreateDTO create(ArticleCreateDTO dto, Integer profileId) {
        // check
        ArticleEntity article = new ArticleEntity();
        article.setTitle(dto.getTitle());
        article.setDescription(dto.getDescription());
        article.setContent(dto.getContent());
        article.setRegionId(dto.getRegionId());
        article.setStatus(ArticleStatus.NotPublished);
        article.setPublisherId(42);
        article.setModeratorId(profileId);
        article.setPhotoId(dto.getPhotoId());
        article.setVisible(true);
        article.setCategoryId(dto.getCategoryId());
        // category ..
        articleRepository.save(article);

        articleTypesService.merge(article.getId(), dto.getArticleType());
        // article.setModerator(null); //  profileId
        return dto;
    }

    public void update(String articleId, ArticleCreateDTO dto, Integer profileId) {
        Optional<ArticleEntity> optional = articleRepository.findById(articleId);
        // check
        ArticleEntity article = optional.get();
        article.setTitle(dto.getTitle());
        article.setDescription(dto.getDescription());
        article.setContent(dto.getContent());
        article.setCategoryId(dto.getCategoryId());
        /*RegionEntity region = new RegionEntity();
        region.setId(dto.getRegionId());
        article.setRegion(region);*/
        article.setRegionId(dto.getRegionId());
        article.setModeratorId(profileId);
        article.setPhotoId(dto.getPhotoId());

        // category ..
        articleRepository.save(article);

        articleTypesService.merge(article.getId(), dto.getArticleType());
        // article.setModerator(null); //  profileId
    }
}