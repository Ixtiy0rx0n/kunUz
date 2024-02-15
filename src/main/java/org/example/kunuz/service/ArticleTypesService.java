package org.example.kunuz.service;

import org.example.kunuz.entity.ArticleTypesEntity;
import org.example.kunuz.repository.ArticleTypesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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


    public void merge(String articleId, List<Integer> typesIdList) {
        // create
        // [] old
        // [1,2,3,4,5] new

        // update
        //[1,2,3,4,5] - old
        //[7,5] - new
    }


}
