package org.example.kunuz.service;

import org.example.kunuz.dto.ArticleDTO;
import org.example.kunuz.entity.ArticleEntity;
import org.example.kunuz.entity.RegionEntity;
import org.example.kunuz.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticleService {
    @Autowired
    private ArticleRepository articleRepository;

//    public ArticleDTO create(ArticleDTO dto){
//        ArticleEntity entity = new ArticleEntity();
//        entity.setContent(dto.getContent());
//        entity.setDescription(dto.getDescription());
//        entity.setPublisher();
//
//    }
}
