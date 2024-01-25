package org.example.kunuz.service;

import org.example.kunuz.dto.AuthDtO;
import org.example.kunuz.dto.ProfileDTO;
import org.example.kunuz.entity.ProfileEntity;
import org.example.kunuz.exp.AppBadException;
import org.example.kunuz.repository.ProfileRepository;
import org.example.kunuz.util.MDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private ProfileRepository profileRepository;
    public ProfileDTO auth(AuthDtO profile){ // login
        Optional<ProfileEntity> optional = profileRepository.findByEmailAndPassword(profile.getEmail(),
                MDUtil.encode(profile.getPassword()));
        if (optional.isEmpty()){
            throw new AppBadException("Email or Password is wrong!");
        }
        ProfileEntity entity = optional.get();
        ProfileDTO dto = new ProfileDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setRole(entity.getRole());
        dto.setSurname(entity.getSurname());
        return dto;
    }

}
