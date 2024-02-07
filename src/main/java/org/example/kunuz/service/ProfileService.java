package org.example.kunuz.service;

import org.example.kunuz.dto.ProfileDTO;
import org.example.kunuz.entity.ProfileEntity;
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


    public ProfileDTO create(ProfileDTO dto) {
        if (dto.getName() == null || dto.getName().trim().length() <= 1) {
            throw new AppBadException("Profile name required");
        }
        if (dto.getSurname() == null || dto.getSurname().trim().length() <= 1) {
            throw new AppBadException("Profile surname required");
        }
        if (dto.getEmail() == null || dto.getEmail().trim().length() < 8) {
            throw new AppBadException("Profile email required");
        }
        if (dto.getPassword() == null || dto.getPassword().trim().isEmpty()) {
            throw new AppBadException("Profile password required");
        }
        ProfileEntity entity = new ProfileEntity();
        if (dto.getStatus() == null) {
            entity.setStatus(ProfileStatus.ACTIVE);
        } else {
            entity.setStatus(dto.getStatus());
        }
        if (dto.getRole() == null) {
            entity.setRole(ProfileRole.USER);
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

    public ProfileDTO update(ProfileDTO dto, Integer id) {
        ProfileEntity entity = get(id);

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
        if (dto.getRole() != null) {
            entity.setRole(dto.getRole());
        } else {
            dto.setRole(entity.getRole());
        }
        if (dto.getStatus() != null) {
            entity.setStatus(dto.getStatus());
        } else {
            dto.setStatus(entity.getStatus());
        }
        entity.setUpdatedDate(LocalDateTime.now());
        profileRepository.update(dto.getName(), dto.getSurname(), dto.getRole(), dto.getStatus(), entity.getId());
        return dto;
    }

    public Boolean delete(Integer id) {
        ProfileEntity profileEntity = get(id);
        Integer effectiveRows = profileRepository.deleteByIdProfile(profileEntity.getId());
        if (effectiveRows == 0) {
            throw new AppBadException("Profile not found");
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

    private ProfileEntity get(Integer id) {
        return profileRepository.findById(id).orElseThrow(() -> new AppBadException("Profile not found"));
    }


    public ProfileDTO updateDetail(ProfileDTO dto, Integer id) {
        ProfileEntity entity = get(id);
        if (entity == null) {
            throw new AppBadException("Profile not found");
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

