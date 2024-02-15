package org.example.kunuz.service;

import org.example.kunuz.dto.CategoryDTO;
import org.example.kunuz.entity.CategoryEntity;
import org.example.kunuz.enums.AppLanguage;
import org.example.kunuz.exp.AppBadException;
import org.example.kunuz.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class CategorySevice {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ResourceBundleService resourceBundleService;

    public CategoryDTO create(CategoryDTO dto) {
        CategoryEntity entity = new CategoryEntity();
        entity.setNameUz(dto.getNameUz());
        entity.setNameRu(dto.getNameRu());
        entity.setNameEn(dto.getNameEn());
        entity.setOrderNumber(dto.getOrderNumber());
        categoryRepository.save(entity);
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setId(entity.getId());
        return dto;
    }

    public CategoryDTO updateById(Integer id, CategoryDTO dto, AppLanguage appLanguage) {
        Optional<CategoryEntity> optional = categoryRepository.findById(id);
        if (optional.isEmpty()) {
            throw new AppBadException(resourceBundleService.getMessage("category.not.found", appLanguage));
        }
        CategoryEntity entity = optional.get();
        entity.setNameUz(dto.getNameUz());
        entity.setNameRu(dto.getNameRu());
        entity.setNameEn(dto.getNameEn());
        entity.setOrderNumber(dto.getOrderNumber());
        categoryRepository.save(entity);
        dto.setId(entity.getId());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setUpdatedDate(entity.getUpdatedDate());
        return dto;
    }

    public void deleteById(Integer id) {
        categoryRepository.delete(id);
    }

    public PageImpl getAll(Integer page, Integer size) {
        Sort sort = Sort.by(Sort.Direction.DESC, "createdDate");

        Pageable paging = PageRequest.of(page - 1, size, sort);
        Page<CategoryEntity> categoryPage = categoryRepository.findAll(paging);
        List<CategoryEntity> entityList = categoryPage.getContent();
        Long totalElement = categoryPage.getTotalElements();

        List<CategoryDTO> dtoList = new LinkedList<>();
        for (CategoryEntity entity : entityList) {
            dtoList.add(toDTO(entity));
        }

        return new PageImpl<>(dtoList, paging, totalElement);
    }

    public Optional<CategoryDTO> getByLang(Integer id, String lang, AppLanguage appLanguage) {
        Optional<CategoryEntity> optional = categoryRepository.findById(id);
        if (optional.isEmpty()) {
            throw new AppBadException(resourceBundleService.getMessage("language.not.found", appLanguage));
        }
        CategoryEntity entity = optional.get();
        CategoryDTO dto = new CategoryDTO();
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


    public CategoryDTO toDTO(CategoryEntity entity) {
        CategoryDTO dto = new CategoryDTO();
        dto.setId(entity.getId());
        dto.setNameUz(entity.getNameUz());
        dto.setNameRu(dto.getNameRu());
        dto.setNameEn(dto.getNameEn());
        dto.setOrderNumber(entity.getOrderNumber());
        dto.setCreatedDate(entity.getCreatedDate());
        return dto;
    }
}
