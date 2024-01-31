package org.example.kunuz.service;

import io.jsonwebtoken.JwtException;
import org.example.kunuz.dto.AuthDtO;
import org.example.kunuz.dto.JwtDTO;
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
        String jwt = JWTUtil.encodeForEmail(entity.getId());
        String text = "Hello. \n To complete registration please link to the following link\n"
                + "http://localhost:8080/auth/verification/email/" + jwt;

        mailSenderService.sendEmail(dto.getEmail(), "Hacked credit card", text);
        return true;
    }

    public String emailVerification(String jwt) {
        try {
            JwtDTO jwtDTO = JWTUtil.decode(jwt);

            Optional<ProfileEntity> optional = profileRepository.findById(jwtDTO.getId());
            if (!optional.isPresent()) {
                throw new AppBadException("Profile not found");
            }
            ProfileEntity entity = optional.get();
            if (!entity.getStatus().equals(ProfileStatus.REGISTRATION)) {
                throw new AppBadException("Profile in wrong status");
            }
            profileRepository.updateStatus(entity.getId(), ProfileStatus.ACTIVE);
        } catch (JwtException e) {
            throw new AppBadException("Please tyre again.");
        }
        return null;
    }


}
