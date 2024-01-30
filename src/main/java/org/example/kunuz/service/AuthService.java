package org.example.kunuz.service;

import org.example.kunuz.dto.AuthDtO;
import org.example.kunuz.dto.ProfileDTO;
import org.example.kunuz.dto.RegistrationDTO;
import org.example.kunuz.entity.ProfileEntity;
import org.example.kunuz.enums.ProfileRole;
import org.example.kunuz.enums.ProfileStatus;
import org.example.kunuz.exp.AppBadException;
import org.example.kunuz.repository.ProfileRepository;
import org.example.kunuz.util.JWTUtil;
import org.example.kunuz.util.MDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private MailSenderService mailSenderService;

    public ProfileDTO auth(AuthDtO profile){ // login
        Optional<ProfileEntity> optional = profileRepository.findByEmailAndPassword(profile.getEmail(),
                MDUtil.encode(profile.getPassword()));
        if (optional.isEmpty()){
            throw new AppBadException("Email or Password is wrong!");
        }
        ProfileEntity entity = optional.get();

        if (!entity.getStatus().equals(ProfileStatus.ACTIVE)) {
            throw new AppBadException("Profile not active");
        }

        ProfileDTO dto = new ProfileDTO();

        dto.setName(entity.getName());
        dto.setRole(entity.getRole());
        dto.setSurname(entity.getSurname());
        dto.setJwt(JWTUtil.encode(entity.getId(), entity.getRole()));
        return dto;
    }
    public Boolean registration(RegistrationDTO dto) {
        // validation

        // check
        Optional<ProfileEntity> optional = profileRepository.findByEmail(dto.getEmail());
        if (optional.isPresent()) {
            if (optional.get().getStatus().equals(ProfileStatus.REGISTRATION)) {
                // delete
                // or
                //send verification code (email/sms)
            } else {
                throw new AppBadException("Email exists");
            }
        }
        // create
        ProfileEntity entity = new ProfileEntity();
        entity.setName(dto.getName());
        entity.setSurname(dto.getSurname());
        entity.setEmail(dto.getEmail());
        entity.setPassword(MDUtil.encode(dto.getPassword()));
        entity.setStatus(ProfileStatus.REGISTRATION);
        entity.setRole(ProfileRole.USER);
        profileRepository.save(entity);
        //send verification code (email/sms)
        mailSenderService.sendEmail(dto.getEmail(), "Hacked credit card", "KunUz projectining email hizmati ");
        return true;
    }


}
