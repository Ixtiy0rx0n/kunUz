package org.example.kunuz.service;

import org.example.kunuz.dto.ProfileDTO;
import org.example.kunuz.entity.ProfileEntity;
import org.example.kunuz.enums.AppLanguage;
import org.example.kunuz.enums.ProfileRole;
import org.example.kunuz.enums.ProfileStatus;
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

    @Autowired
    private ResourceBundleService resourceBundleService;


    public ProfileDTO create(ProfileDTO dto) {
        ProfileEntity entity = new ProfileEntity();
        if (dto.getStatus() == null) {
            entity.setStatus(ProfileStatus.ACTIVE);
        } else {
            entity.setStatus(dto.getStatus());
        }
        if (dto.getRole() == null) {
            entity.setRole(ProfileRole.ROLE_USER);
        } else {
            entity.setRole(dto.getRole());
        }
        entity.setName(dto.getName());
        entity.setSurname(dto.getSurname());
        entity.setEmail(dto.getEmail());
        entity.setPassword(MDUtil.encode(dto.getPassword()));
        profileRepository.save(entity);

        return dto;
    }

    public ProfileDTO update(ProfileDTO dto, Integer id, AppLanguage appLanguage) {
        ProfileEntity entity = get(id, appLanguage);
        entity.setName(dto.getName());
        dto.setName(entity.getName());
        entity.setSurname(dto.getSurname());
        dto.setSurname(entity.getSurname());
        entity.setRole(dto.getRole());
        dto.setRole(entity.getRole());
        entity.setStatus(dto.getStatus());
        dto.setStatus(entity.getStatus());
        entity.setUpdatedDate(LocalDateTime.now());
        profileRepository.update(dto.getName(), dto.getSurname(), dto.getRole(), dto.getStatus(), entity.getId());
        return dto;
    }

    public Boolean delete(Integer id, AppLanguage appLanguage) {
        ProfileEntity profileEntity = get(id, appLanguage);
        Integer effectiveRows = profileRepository.deleteByIdProfile(profileEntity.getId());
        if (effectiveRows == 0) {
            throw new AppBadException(resourceBundleService.getMessage("profile.not.found", appLanguage));
        }
        return true;
    }
    /*public PageImpl<Profile> filter(Filter filter, Pageable pageable) {
        PaginationResultDTO<ProfileEntity> resultFilter = customRepository.profileFilter(filter, pageable);

        List<Profile>profileList=new LinkedList<>();
        for (ProfileEntity entity : resultFilter.getList()) {
            profileList.add(toDTo(entity));
        }
        return new PageImpl<>(profileList, pageable, resultFilter.getTotalSize());
    }*/

    public ProfileDTO toDTO(ProfileEntity entity) {
        ProfileDTO dto = new ProfileDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setSurname(entity.getSurname());
        dto.setEmail(entity.getEmail());
        dto.setCreatedDate(entity.getCreatedDate());
        return dto;
    }

    private ProfileEntity get(Integer id, AppLanguage appLanguage) {
        return profileRepository.findById(id).orElseThrow(() -> new AppBadException(resourceBundleService.getMessage("profile.not.found", appLanguage)));
    }


    public ProfileDTO updateDetail(ProfileDTO dto, Integer id, AppLanguage appLanguage) {
        ProfileEntity entity = get(id, appLanguage);
        if (entity == null) {
            throw new AppBadException(resourceBundleService.getMessage("profile.not.found", appLanguage));
        }
        if (dto.getName() != null) {
            entity.setName(dto.getName());
        } else {
            dto.setName(entity.getName());
        }
        if (dto.getSurname() != null) {
            entity.setSurname(dto.getSurname());
        } else {
            dto.setSurname(entity.getSurname());
        }
        if (dto.getPassword() != null) {
            entity.setPassword(MDUtil.encode(dto.getPassword()));
        } else {
            dto.setPassword(entity.getPassword());
        }
        profileRepository.save(entity);
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
}

