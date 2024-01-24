package org.example.kunuz.service;

import org.example.kunuz.dto.RegionDTO;

import org.example.kunuz.entity.RegionEntity;
import org.example.kunuz.enums.AppLanguage;
import org.example.kunuz.exp.AppBadException;
import org.example.kunuz.repository.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
        RegionEntity entity = get(id);
        entity.setNameUz(dto.getNameUz());
        entity.setNameRu(dto.getNameRu());
        entity.setNameEn(dto.getNameEn());
        entity.setOrderNumber(dto.getOrderNumber());
        regionRepository.save(entity);
        dto.setId(entity.getId());
        return dto;
    }

    public Boolean deleteById(Integer id) {
        RegionEntity regionEntity = get(id);
        int effectedRows = regionRepository.delete(id);
        return true;
    }

    public PageImpl getAll(Integer page, Integer size) {
        Sort sort = Sort.by(Sort.Direction.DESC, "createdDate");

        Pageable paging = PageRequest.of(page - 1, size, sort);
        Page<RegionEntity> articleTypePage = regionRepository.findAllByVisible(paging,true);
        List<RegionEntity> entityList = articleTypePage.getContent();
        Long totalElement = articleTypePage.getTotalElements();

        List<RegionDTO> dtoList = new LinkedList<>();
        for (RegionEntity entity : entityList) {
            dtoList.add(toDTO(entity));
        }

        return new PageImpl<>(dtoList, paging, totalElement);
    }

    public List<RegionDTO> getByLang(AppLanguage language) {
        List<RegionDTO> dtoList = new LinkedList<>();
        Iterable<RegionEntity> all = regionRepository.findAll();
        for (RegionEntity entity : all) {
            RegionDTO dto = new RegionDTO();
            dto.setId(entity.getId());
            switch (language) {
                case uz -> dto.setName(entity.getNameUz());
                case ru -> dto.setName(entity.getNameRu());
                default -> dto.setName(entity.getNameEn());
            }
            dtoList.add(dto);
        }

        return dtoList;

    }




    public RegionEntity get(Integer id) {
        return regionRepository.findById(id).orElseThrow(() -> {
            throw new AppBadException("Region not found");
        });
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
