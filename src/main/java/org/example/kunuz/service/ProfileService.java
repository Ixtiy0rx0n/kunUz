package org.example.kunuz.service;

import org.example.kunuz.dto.ProfileDTO;
import org.example.kunuz.dto.RegionDTO;
import org.example.kunuz.entity.ProfileEntity;
import org.example.kunuz.entity.RegionEntity;
import org.example.kunuz.exp.AppBadException;
import org.example.kunuz.repository.ProfileRepository;
import org.example.kunuz.util.MDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

@Service
public class ProfileService {

    @Autowired
    private ProfileRepository profileRepository;
    public ProfileDTO create(ProfileDTO dto) {
        ProfileEntity entity = new ProfileEntity();
        entity.setName(dto.getName());
        entity.setSurname(dto.getSurname());
        entity.setEmail(dto.getEmail());
        entity.setPassword(MDUtil.encode(dto.getPassword()));
        entity.setStatus(dto.getStatus());
        entity.setRole(dto.getRole());
        profileRepository.save(entity);
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setId(entity.getId());
        return dto;
    }
    public ProfileDTO updateById(Integer id, ProfileDTO dto) {
        ProfileEntity entity = get(id);
        if (!(dto.getName()==null)){
            entity.setName(dto.getName());
        }
        if (!(dto.getSurname()==null)) {
            entity.setSurname(dto.getSurname());
        }
        if (!(dto.getEmail()==null)) {
            entity.setEmail(dto.getEmail());
        }
        if (!(dto.getStatus()==null)) {
            entity.setStatus(dto.getStatus());
        }
        if (!(dto.getRole()==null)) {
            entity.setRole(dto.getRole());
        }
        if (!(dto.getVisible()==null)) {
            entity.setVisible(dto.getVisible());
        }
        entity.setUpdatedDate(LocalDateTime.now());
        profileRepository.save(entity);
        dto.setId(entity.getId());
        return dto;
    }

    public PageImpl getAll(Integer page, Integer size) {
        Sort sort = Sort.by(Sort.Direction.DESC, "createdDate");

        Pageable paging = PageRequest.of(page - 1, size, sort);
        Page<ProfileEntity> profilePage = profileRepository.findAllByVisible(paging, true);
        List<ProfileEntity> entityList = profilePage.getContent();
        Long totalElement = profilePage.getTotalElements();

        List<ProfileDTO> dtoList = new LinkedList<>();
        for (ProfileEntity entity : entityList) {
            dtoList.add(toDTO(entity));
        }

        return new PageImpl<>(dtoList, paging, totalElement);
    }
    public Boolean deleteById(Integer id){
        ProfileEntity profileEntity = get(id);
        int effectedRows = profileRepository.delete(id);
        return true;
    }














    public ProfileEntity get(Integer id) {
        return profileRepository.findById(id).orElseThrow(() -> {
            throw new AppBadException("Profile not found");
        });
    }

    public ProfileDTO toDTO(ProfileEntity entity) {
        ProfileDTO dto = new ProfileDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setSurname(entity.getSurname());
        dto.setEmail(entity.getEmail());
        dto.setRole(entity.getRole());
        dto.setVisible(entity.getVisible());
        dto.setStatus(entity.getStatus());
        dto.setPassword(entity.getPassword());
        dto.setCreatedDate(entity.getCreatedDate());
        return dto;
    }
}
