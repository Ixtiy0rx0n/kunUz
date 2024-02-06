package org.example.kunuz.service;

import org.example.kunuz.entity.ArticleTypesEntity;
import org.example.kunuz.exp.AppBadException;
import org.example.kunuz.repository.ArticleTypesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class ArticleTypesService {
    @Autowired
    private ArticleTypesRepository articleTypesRepository;

    public void create(String articleId, List<Integer> typesIdList) {
        for (Integer typeId : typesIdList) {
            create(articleId, typeId);
        }
    }

    public void create(String articleId, Integer typesId) {
        ArticleTypesEntity entity = new ArticleTypesEntity();
        entity.setArticleId(articleId);
        entity.setTypesId(typesId);
        articleTypesRepository.save(entity);
    }


}
