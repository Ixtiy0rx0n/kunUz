package org.example.kunuz.service;

import org.example.kunuz.dto.ArticleTypeDTO;
import org.example.kunuz.entity.ArticleTypeEntity;
import org.example.kunuz.exp.AppBadException;
import org.example.kunuz.repository.ArticleTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
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

    public ArticleTypeDTO updateById(Integer id, ArticleTypeDTO dto) {
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

    public void deleteById(Integer id) {
        articleTypeRepository.delete(id);

    }

    public PageImpl getAll(Integer page, Integer size) {
        Sort sort = Sort.by(Sort.Direction.DESC, "createdDate");
        Pageable paging = PageRequest.of(page - 1, size, sort);
        Page<ArticleTypeEntity> articleTypePage = articleTypeRepository.findAll(paging);
        List<ArticleTypeEntity> entityList = articleTypePage.getContent();
        Long totalElement = articleTypePage.getTotalElements();

        List<ArticleTypeDTO> dtoList = new LinkedList<>();
        for (ArticleTypeEntity entity : entityList) {
            dtoList.add(toDTO(entity));
        }

        return new PageImpl<>(dtoList, paging, totalElement);
    }

    public Optional<ArticleTypeDTO> getByLang(Integer id, String lang) {
        Optional<ArticleTypeEntity> articleTypeOptional = articleTypeRepository.findById(id);

        if (articleTypeOptional.isEmpty()) {
            throw new AppBadException("Lang Not found ❌❌❌");
        }
        ArticleTypeEntity entity = articleTypeOptional.get();
        ArticleTypeDTO dto = new ArticleTypeDTO();
        dto.setId(entity.getId());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setOrderNumber(entity.getOrderNumber());

        switch (lang) {
            case "uz" -> dto.setNameUz(entity.getNameUz());
            case "ru" -> dto.setNameRu(entity.getNameRu());
            case "en" -> dto.setNameEn(entity.getNameEn());
            default -> dto.setNameUz(entity.getNameUz());
        }


        return Optional.of(dto);

    }


    public ArticleTypeDTO toDTO(ArticleTypeEntity entity) {
        ArticleTypeDTO dto = new ArticleTypeDTO();
        dto.setId(entity.getId());
        dto.setNameUz(entity.getNameUz());
        dto.setNameRu(dto.getNameRu());
        dto.setNameEn(dto.getNameEn());
        dto.setOrderNumber(entity.getOrderNumber());
        dto.setCreatedDate(entity.getCreatedDate());
        return dto;
    }

}
