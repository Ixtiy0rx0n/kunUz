package org.example.kunuz.service;

import org.example.kunuz.dto.RegionDTO;

import org.example.kunuz.entity.RegionEntity;

import org.example.kunuz.exp.AppBadException;
import org.example.kunuz.repository.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class RegionService {
    @Autowired
    private RegionRepository regionRepository;
    public RegionDTO create(RegionDTO dto) {
        RegionEntity entity = new RegionEntity();
        entity.setNameUz(dto.getNameUz());
        entity.setNameRu(dto.getNameRu());
        entity.setNameEn(dto.getNameEn());
        entity.setOrderNumber(dto.getOrderNumber());
        regionRepository.save(entity);
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setId(entity.getId());
        return dto;
    }

    public RegionDTO updateById(Integer id, RegionDTO dto) {
        Optional<RegionEntity> optional = regionRepository.findById(id);
        if (optional.isEmpty()) {
            throw new AppBadException("Region not found");
        }
        RegionEntity entity = optional.get();
        entity.setNameUz(dto.getNameUz());
        entity.setNameRu(dto.getNameRu());
        entity.setNameEn(dto.getNameEn());
        entity.setOrderNumber(dto.getOrderNumber());
        regionRepository.save(entity);
        dto.setId(entity.getId());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setUpdatedDate(entity.getUpdatedDate());
        return dto;
    }

    public void deleteById(Integer id){
        regionRepository.deleteById(id);
    }

    public PageImpl getAll(Integer page, Integer size) {
        Sort sort = Sort.by(Sort.Direction.DESC, "createdDate");

        Pageable paging = PageRequest.of(page - 1, size, sort);
        Page<RegionEntity> articleTypePage = regionRepository.findAll(paging);
        List<RegionEntity> entityList = articleTypePage.getContent();
        Long totalElement = articleTypePage.getTotalElements();

        List<RegionDTO> dtoList = new LinkedList<>();
        for (RegionEntity entity : entityList) {
            dtoList.add(toDTO(entity));
        }

        return new PageImpl<>(dtoList, paging, totalElement);
    }

    public Optional<RegionDTO> getByLang(Integer id, String lang) {
        Optional<RegionEntity> optional = regionRepository.findById(id);

        if (optional.isEmpty()) {
            throw new AppBadException("Lang Not found ❌❌❌");
        }
        RegionEntity entity = optional.get();
        RegionDTO dto = new RegionDTO();
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









    public RegionDTO toDTO(RegionEntity entity) {
        RegionDTO dto = new RegionDTO();
        dto.setId(entity.getId());
        dto.setNameUz(entity.getNameUz());
        dto.setNameRu(dto.getNameRu());
        dto.setNameEn(dto.getNameEn());
        dto.setOrderNumber(entity.getOrderNumber());
        dto.setCreatedDate(entity.getCreatedDate());
        return dto;
    }
}
