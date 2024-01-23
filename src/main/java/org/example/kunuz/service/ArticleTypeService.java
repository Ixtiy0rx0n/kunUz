package org.example.kunuz.service;

import org.example.kunuz.dto.ArticleTypeDTO;
import org.example.kunuz.entity.ArticleTypeEntity;
import org.example.kunuz.exp.AppBadException;
import org.example.kunuz.repository.ArticleTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ArticleTypeService {
    @Autowired
    private ArticleTypeRepository articleTypeRepository;

    public ArticleTypeDTO create(ArticleTypeDTO dto) {
        ArticleTypeEntity entity = new ArticleTypeEntity();
        entity.setNameUz(dto.getNameUz());
        entity.setNameRu(dto.getNameRu());
        entity.setNameEn(dto.getNameEn());
        entity.setOrderNumber(dto.getOrderNumber());
        articleTypeRepository.save(entity);
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setId(entity.getId());
        return dto;
    }
    public ArticleTypeDTO updateById(Integer id, ArticleTypeDTO dto){
        Optional<ArticleTypeEntity> optional = articleTypeRepository.findById(id);
        if (optional.isEmpty()) {
            throw new AppBadException("ArticleType not found");
        }
        ArticleTypeEntity entity = optional.get();
        entity.setNameUz(dto.getNameUz());
        entity.setNameRu(dto.getNameRu());
        entity.setNameEn(dto.getNameEn());
        entity.setOrderNumber(dto.getOrderNumber());
        articleTypeRepository.save(entity);
        dto.setId(entity.getId());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setUpdatedDate(entity.getUpdatedDate());
        return dto;
    }
}
