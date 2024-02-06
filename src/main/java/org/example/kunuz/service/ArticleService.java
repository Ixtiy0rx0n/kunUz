package org.example.kunuz.service;

import org.example.kunuz.entity.ArticleEntity;
import org.example.kunuz.exp.AppBadException;
import org.example.kunuz.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticleService {
    @Autowired
    private ArticleRepository articleRepository;

    public ArticleEntity get(String id) {
        return articleRepository.findById(id).orElseThrow(() -> {
            throw new AppBadException("Article not found");
        });
    }
}